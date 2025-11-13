package com.dm_genie_logiciel.fera.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "utilisateurs")
public class Utilisateurs {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nom;
    public String email;

    public Utilisateurs(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }
}