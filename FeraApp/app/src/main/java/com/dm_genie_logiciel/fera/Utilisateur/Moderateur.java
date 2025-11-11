package com.dm_genie_logiciel.fera.Utilisateur;

public class Moderateur extends Utilisateur {

    public Moderateur(String nom, String prenom, String email, String pseudo, String motDePasse) {
        super(nom, prenom, email, pseudo, motDePasse, Role.MODERATEUR);
    }

    public void validerUpload(String idUpload) {ajouterAction("Upload " + idUpload + " validé");}

    public void bannirUtilisateur(Utilisateur user) {
        ajouterAction("Utilisateur " + user.getPseudo() + " banni");
    }

    @Override
    public String getRoleDescription() {return "Modérateur (valide ou rejette les uploads)";}
}
