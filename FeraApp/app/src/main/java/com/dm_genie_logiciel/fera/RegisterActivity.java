package com.dm_genie_logiciel.fera;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dm_genie_logiciel.fera.Utilisateur.UserManager;
import com.dm_genie_logiciel.fera.Utilisateur.Utilisateur;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputPseudo, inputMotDePasse;
    private Button btnConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        inputPseudo = findViewById(R.id.editTextPseudo);
        inputMotDePasse = findViewById(R.id.editTextPassword);
        btnConnexion = findViewById(R.id.buttonRegister);

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pseudo = inputPseudo.getText().toString().trim();
                String motDePasse = inputMotDePasse.getText().toString().trim();

                Utilisateur user = UserManager.connecter(pseudo, motDePasse);

                if (user == null) {
                    SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("pseudo", user.getPseudo());
                    editor.putString("role", user.getRole().toString());
                    editor.apply();

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Identifiants invalides", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}