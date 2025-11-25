package com.dm_genie_logiciel.fera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import com.dm_genie_logiciel.fera.database.AppDatabase;
import com.dm_genie_logiciel.fera.database.Utilisateurs;
import com.dm_genie_logiciel.fera.database.UtilisateursDao;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnLogout, buttonUploadImage;
    TextView textUserInfo;
    ImageView imagePreview;
    LinearLayout galleryContainer;

    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);
        buttonUploadImage = findViewById(R.id.buttonUploadImage);
        imagePreview = findViewById(R.id.imagePreview);
        textUserInfo = findViewById(R.id.textUserInfo);
        galleryContainer = findViewById(R.id.galleryContainer);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String pseudo = prefs.getString("pseudo", "Utilisateur");
        String role = prefs.getString("role", "Visiteur");

        textUserInfo.setText("Connecté en tant que : " + pseudo + " (" + role + ")");

        buttonUploadImage.setOnClickListener(v -> {
            Intent gallery = new Intent(Intent.ACTION_PICK);
            gallery.setType("image/*");
            gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(gallery, PICK_IMAGE);
        });


        btnLogout.setOnClickListener(v -> {
            SharedPreferences prefsLogout = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            prefsLogout.edit().clear().apply();
            startActivity(new Intent(MainActivity.this, StartActivity.class));
            finish();
        });

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        UtilisateursDao utilisateursDao = db.utilisateursDao();

        new Thread(() -> {
            if (utilisateursDao.getAllUtilisateurs().isEmpty()) {
                utilisateursDao.insert(new Utilisateurs("Dupont","Alice","alice@email.com",
                        "alice123","passwordAlice","UTILISATEUR"));
                utilisateursDao.insert(new Utilisateurs("Martin","Bob","bob@email.com",
                        "bobby","passwordBob","UTILISATEUR"));
            }


            List<Utilisateurs> utilisateursList = utilisateursDao.getAllUtilisateurs();
            for (Utilisateurs u : utilisateursList) {
                System.out.println(u.id + ": " + u.nom + " - " + u.email);
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {

            if (data.getClipData() != null) {
                ClipData clipData = data.getClipData();
                int count = clipData.getItemCount();

                for (int i = 0; i < count; i++) {
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    ajouterImageAlaVue(imageUri);
                }

            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                ajouterImageAlaVue(imageUri);
            }
        }
    }

    private void ajouterImageAlaVue(Uri imageUri) {
        ImageView imageView = new ImageView(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                400
        );
        params.setMargins(0, 20, 0, 20);
        imageView.setLayoutParams(params);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageURI(imageUri);

        galleryContainer.addView(imageView);
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
