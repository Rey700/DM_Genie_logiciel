package com.dm_genie_logiciel.fera.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UtilisateursDao {

    @Insert
    void insert(Utilisateurs utilisateur);

    @Query("SELECT * FROM utilisateurs")
    List<Utilisateurs> getAllUtilisateurs();

    @Query("DELETE FROM utilisateurs")
    void deleteAll();

    @Query("SELECT * FROM utilisateurs WHERE pseudo = :pseudo OR email = :email LIMIT 1")
    Utilisateurs findByPseudoOrEmail(String pseudo, String email);

    @Query("SELECT * FROM utilisateurs WHERE pseudo = :pseudo AND motDePasse = :motDePasse LIMIT 1")
    Utilisateurs login(String pseudo, String motDePasse);
}
