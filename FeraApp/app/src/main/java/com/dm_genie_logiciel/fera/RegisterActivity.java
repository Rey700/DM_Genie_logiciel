package com.dm_genie_logiciel.fera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dm_genie_logiciel.fera.database.AppDatabase;
import com.dm_genie_logiciel.fera.database.Utilisateurs;
import com.dm_genie_logiciel.fera.database.UtilisateursDao;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputPseudo, inputMotDePasse;
    private Button btnConnexion;

    private UtilisateursDao utilisateursDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        inputPseudo = findViewById(R.id.editTextPseudo);
        inputMotDePasse = findViewById(R.id.editTextPassword);
        btnConnexion = findViewById(R.id.buttonRegister);

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        utilisateursDao = db.utilisateursDao();

        btnConnexion.setOnClickListener(v -> {

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

            new Thread(() -> {
                Utilisateurs existant = utilisateursDao.findByPseudoOrEmail(pseudo, email);
                if (existant != null) {
                    runOnUiThread(() ->
                            Toast.makeText(RegisterActivity.this, "Pseudo ou e-mail déjà utilisé", Toast.LENGTH_SHORT).show()
                    );
                    return;
                }

                Utilisateurs nouvelUtilisateur = new Utilisateurs(
                        nom,
                        prenom,
                        email,
                        pseudo,
                        motDePasse,
                        "UTILISATEUR"
                );
                utilisateursDao.insert(nouvelUtilisateur);

                runOnUiThread(() -> {
                    SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("pseudo", pseudo);
                    editor.putString("role", "UTILISATEUR");
                    editor.apply();

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });
            }).start();
        });
    }
}
