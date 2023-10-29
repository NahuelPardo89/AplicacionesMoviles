package com.example.ampamain.modelos;

public class User {
    private String uid;
    private String nombreyapellido;

    private String password;

    private String email;
    private String fotoUrl;

    // Constructor
    public User(String uid, String nombreyapellido,String password, String email, String fotoUrl) {
        this.uid = uid;
        this.nombreyapellido = nombreyapellido;
        this.password = password;
        this.email = email;
        this.fotoUrl = fotoUrl;
    }

    // Getters
    public String getUid() {
        return uid;
    }

    public String getNombreyapellido() {
        return nombreyapellido;
    }

    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    // Setters
    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPassword(String password) { this.password = password;   }

    public void setNombreyapellido(String nombreyapellido) { this.nombreyapellido = nombreyapellido;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}
