package com.dm_genie_logiciel.fera.model;

public class User {
    private int id;
    private String pseudo;
    private String email;
    private String avatarUrl;

    public User(int id, String pseudo, String email, String avatarUrl) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }

    public int getId() { return id; }
    public String getPseudo() { return pseudo; }
    public String getEmail() { return email; }
    public String getAvatarUrl() { return avatarUrl; }
}