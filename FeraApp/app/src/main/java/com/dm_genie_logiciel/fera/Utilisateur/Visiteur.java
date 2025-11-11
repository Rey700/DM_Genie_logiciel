package com.dm_genie_logiciel.fera.Utilisateur;

public class Visiteur extends Utilisateur {

    public Visiteur(String pseudo) {
        super("", "", "", pseudo, "", Role.VISITEUR);
    }

    @Override
    public String getRoleDescription() {return "Visiteur (accès lecture seule, sans compte)";}
}
