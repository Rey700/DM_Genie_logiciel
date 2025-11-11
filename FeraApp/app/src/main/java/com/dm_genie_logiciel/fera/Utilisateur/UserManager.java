package com.dm_genie_logiciel.fera.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static List<Utilisateur> utilisateurs = new ArrayList<>();

    static {
        Admin admin = new Admin(
                "Admin",
                "Système",
                "admin@fera.com",
                "admin",
                "admin123"
        );
        utilisateurs.add(admin);
    }

    public static boolean inscrireUtilisateur(Utilisateur user) {
        for (Utilisateur u : utilisateurs) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail()) ||
                    u.getPseudo().equalsIgnoreCase(user.getPseudo())) {
                return false;
            }
        }
        utilisateurs.add(user);
        return true;
    }

    public static Utilisateur connecter(String pseudo, String motDePasse) {
        for (Utilisateur u : utilisateurs) {
            if (u.getPseudo().equals(pseudo) && u.getMotDePasse().equals(motDePasse)) {
                return u;
            }
        }
        return null;
    }

    public static List<Utilisateur> getUtilisateurs() {return utilisateurs;}
}

