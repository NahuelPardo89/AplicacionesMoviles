package com.example.ampamain;

public class UserProfile {
    private Integer dni;
    private String nombre;
    private String apellido;
    private String email;
    private boolean isActive;
    private String fotoUrl;

    // Constructor
    public UserProfile(Integer dni, String nombre, String apellido, String email, boolean isActive, String fotoUrl) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.isActive = isActive;
        this.fotoUrl = fotoUrl;
    }

    // Getters
    public Integer getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    // Setters
    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}
