package com.dm_genie_logiciel.fera.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "utilisateurs")
public class Utilisateurs {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nom;
    public String prenom;
    public String email;
    public String pseudo;
    public String motDePasse;
    public String role;

    public Utilisateurs(String nom,
                        String prenom,
                        String email,
                        String pseudo,
                        String motDePasse,
                        String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.role = role;
    }
}
