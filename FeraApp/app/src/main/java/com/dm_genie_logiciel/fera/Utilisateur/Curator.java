package com.dm_genie_logiciel.fera.Utilisateur;

public class Curator extends Utilisateur {

    public Curator(String nom, String prenom, String email, String pseudo, String motDePasse) {
        super(nom, prenom, email, pseudo, motDePasse, Role.CURATOR);
    }

    public void validerLabel(String labelId) {ajouterAction("Label " + labelId + " validé");}

    @Override
    public String getRoleDescription() {
        return "Curator (valide les labels proposés par les utilisateurs)";
    }
}
