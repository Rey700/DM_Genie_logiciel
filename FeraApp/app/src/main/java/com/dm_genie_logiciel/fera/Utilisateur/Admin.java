package com.dm_genie_logiciel.fera.Utilisateur;

public class Admin extends Utilisateur {

    public Admin(String nom, String prenom, String email, String pseudo, String motDePasse) {
        super(nom, prenom, email, pseudo, motDePasse, Role.ADMIN);
    }

    public void supprimerCompte(Utilisateur user) {
        ajouterAction("Suppression du compte de " + user.getPseudo());
    }

    public void gererRoles(Utilisateur user, Role nouveauRole) {
        user.role = nouveauRole;
        ajouterAction("Changement du rôle de " + user.getPseudo() + " en " + nouveauRole);
    }

    @Override
    public String getRoleDescription() {return "Administrateur (gère le système et les utilisateurs)";}
}