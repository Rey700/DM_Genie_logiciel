package com.dm_genie_logiciel.fera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dm_genie_logiciel.fera.database.AppDatabase;
import com.dm_genie_logiciel.fera.database.Utilisateurs;
import com.dm_genie_logiciel.fera.database.UtilisateursDao;

public class LoginActivity extends AppCompatActivity {

    private EditText inputPseudo, inputMotDePasse;
    private Button btnConnexion;
    private TextView textRegisterLink;

    private UtilisateursDao utilisateursDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        inputPseudo = findViewById(R.id.editTextPseudo);
        inputMotDePasse = findViewById(R.id.editTextPassword);
        btnConnexion = findViewById(R.id.buttonLogin);
        textRegisterLink = findViewById(R.id.textRegisterLink);

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        utilisateursDao = db.utilisateursDao();

        btnConnexion.setOnClickListener(v -> {
            String pseudo = inputPseudo.getText().toString().trim();
            String motDePasse = inputMotDePasse.getText().toString().trim();

            if (pseudo.isEmpty() || motDePasse.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                Utilisateurs u = utilisateursDao.login(pseudo, motDePasse);

                runOnUiThread(() -> {
                    if (u != null) {
                        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("pseudo", u.pseudo);
                        editor.putString("role", u.role);
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Identifiants invalides", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });

        textRegisterLink.setOnClickListener(v2 -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
