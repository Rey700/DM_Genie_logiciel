package com.dm_genie_logiciel.fera;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dm_genie_logiciel.fera.Utilisateur.UserManager;
import com.dm_genie_logiciel.fera.Utilisateur.Utilisateur;

public class LoginActivity extends AppCompatActivity {

    private EditText inputPseudo, inputMotDePasse;
    private Button btnConnexion;
    private TextView textRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        inputPseudo = findViewById(R.id.editTextPseudo);
        inputMotDePasse = findViewById(R.id.editTextPassword);
        btnConnexion = findViewById(R.id.buttonLogin);
        textRegisterLink = findViewById(R.id.textRegisterLink);

        btnConnexion.setOnClickListener(v -> {
            String pseudo = inputPseudo.getText().toString().trim();
            String motDePasse = inputMotDePasse.getText().toString().trim();

            if (pseudo.isEmpty() || motDePasse.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            Utilisateur user = UserManager.connecter(pseudo, motDePasse);

            if (user != null) {
                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("pseudo", user.getPseudo());
                editor.putString("role", user.getRole().toString());
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Identifiants invalides", Toast.LENGTH_SHORT).show();
            }
        });

        textRegisterLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
