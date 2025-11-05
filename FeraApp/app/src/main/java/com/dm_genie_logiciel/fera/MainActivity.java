package com.dm_genie_logiciel.fera;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Déclaration des champs
    EditText prenomField, nomField, emailField, passwordField;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison des éléments du layout XML
        prenomField = findViewById(R.id.editTextPrenom);
        nomField = findViewById(R.id.editTextNom);
        emailField = findViewById(R.id.editTextEmail);
        passwordField = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.buttonRegister);

        // Action sur le clic du bouton
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom = prenomField.getText().toString().trim();
                String nom = nomField.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                if (prenom.isEmpty() || nom.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(prenom, nom, email, password);
                }
            }
        });
    }

    // Fonction pour enregistrer un utilisateur sur ton serveur
    private void registerUser(String prenom, String nom, String email, String password) {
        new Thread(() -> {
            try {
                // 🔹 Remplace l’URL ci-dessous par celle de ton API ou serveur
                URL url = new URL("https://tonserveur.com/api/register");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);

                // Création du corps JSON à envoyer
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("prenom", prenom);
                jsonParam.put("nom", nom);
                jsonParam.put("email", email);
                jsonParam.put("password", password);

                // Envoi des données
                OutputStream os = conn.getOutputStream();
                os.write(jsonParam.toString().getBytes("UTF-8"));
                os.close();

                // Lecture de la réponse du serveur
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Lecture du message de réponse
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    runOnUiThread(() -> {
                        Toast.makeText(MainActivity.this, "Compte créé avec succès !", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(MainActivity.this, "Erreur lors de la création du compte (" + responseCode + ")", Toast.LENGTH_SHORT).show();
                    });
                }

                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Erreur : " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }
}
