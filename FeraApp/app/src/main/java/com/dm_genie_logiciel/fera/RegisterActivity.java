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
import com.dm_genie_logiciel.fera.Utilisateur.UtilisateurStandard;

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

                String prenom = ((EditText) findViewById(R.id.editTextPrenom)).getText().toString().trim();
                String nom = ((EditText) findViewById(R.id.editTextNom)).getText().toString().trim();
                String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString().trim();
                String pseudo = inputPseudo.getText().toString().trim();
                String motDePasse = inputMotDePasse.getText().toString().trim();
                String confirm = ((EditText) findViewById(R.id.editTextConfirmPassword)).getText().toString().trim();

                if (prenom.isEmpty() || nom.isEmpty() || email.isEmpty() ||
                        pseudo.isEmpty() || motDePasse.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!motDePasse.equals(confirm)) {
                    Toast.makeText(RegisterActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Création d'un nouvel utilisateur standard
                Utilisateur nouvelUtilisateur = new UtilisateurStandard(
                        nom,
                        prenom,
                        email,
                        pseudo,
                        motDePasse
                );

                boolean inscrit = UserManager.inscrireUtilisateur(nouvelUtilisateur);

                if (!inscrit) {
                    Toast.makeText(RegisterActivity.this,
                            "Pseudo ou e-mail déjà utilisé",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // On peut directement le considérer comme connecté
                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("pseudo", nouvelUtilisateur.getPseudo());
                editor.putString("role", nouvelUtilisateur.getRole().toString());
                editor.apply();

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}