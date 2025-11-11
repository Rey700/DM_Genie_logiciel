package com.dm_genie_logiciel.fera.Utilisateur;

public class UtilisateurStandard extends Utilisateur {

    public UtilisateurStandard(String nom, String prenom, String email, String pseudo, String motDePasse) {
        super(nom, prenom, email, pseudo, motDePasse, Role.UTILISATEUR);
    }

    public void uploaderPhoto(String chemin) {ajouterAction("Photo uploadée depuis : " + chemin);}
    public void creerLabel(String label) {ajouterAction("Création du label : " + label);}

    @Override
    public String getRoleDescription() {
        return "Utilisateur (peut uploader et labelliser du contenu)";
    }
}
