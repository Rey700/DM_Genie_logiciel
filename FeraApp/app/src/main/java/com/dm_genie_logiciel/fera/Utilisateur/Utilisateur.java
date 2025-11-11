package com.dm_genie_logiciel.fera.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public abstract class Utilisateur {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String pseudo;
    protected String motDePasse;
    protected Role role;
    protected List<String> historiqueActions;

    public Utilisateur(String nom, String prenom, String email, String pseudo, String motDePasse, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.role = role;
        this.historiqueActions = new ArrayList<>();
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getPseudo() { return pseudo; }
    public String getMotDePasse() { return motDePasse; }
    public Role getRole() { return role; }
    public List<String> getHistoriqueActions() { return historiqueActions; }

    public void setPseudo(String pseudo) { this.pseudo = pseudo; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public void ajouterAction(String action) {historiqueActions.add(action);}

    public abstract String getRoleDescription();
}
