package com.dm_genie_logiciel.fera;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.dm_genie_logiciel.fera.database.AppDatabase;
import com.dm_genie_logiciel.fera.database.Utilisateurs;
import com.dm_genie_logiciel.fera.database.UtilisateursDao;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String pseudo = prefs.getString("pseudo", "Utilisateur");
        String role = prefs.getString("role", "Visiteur");

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        UtilisateursDao utilisateursDao = db.utilisateursDao();

        new Thread(() -> {
            utilisateursDao.insert(new Utilisateurs("Alice", "alice@email.com"));
            utilisateursDao.insert(new Utilisateurs("Bob", "bob@email.com"));

            List<Utilisateurs> utilisateursList = utilisateursDao.getAllUtilisateurs();
            for (Utilisateurs u : utilisateursList) {
                System.out.println(u.id + ": " + u.nom + " - " + u.email);
            }
        }).start();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                prefs.edit().clear().apply();


                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    /*private void registerUser(String prenom, String nom, String email, String password) {
        new Thread(() -> {
            try {

                URL url = new URL("https://tonserveur.com/api/register");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("prenom", prenom);
                jsonParam.put("nom", nom);
                jsonParam.put("email", email);
                jsonParam.put("password", password);

                OutputStream os = conn.getOutputStream();
                os.write(jsonParam.toString().getBytes("UTF-8"));
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
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
        }).start();*/
}
