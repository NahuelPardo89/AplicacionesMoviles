package com.example.ampamain.modelos;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Instalacion")
public class Instalacion {
    @PrimaryKey
    @NonNull
    private int idInstalacion;
    private String nombre;
    private String descripcion;
    private byte[] foto;
    private int costo;

    public Instalacion(int idInstalacion, String nombre,String descripcion, byte[] foto, int costo) {
        this.idInstalacion = idInstalacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.costo = costo;
    }

    // Getters
    public int getIdInstalacion() { return idInstalacion; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public byte[] getFoto() { return foto; }
    public int getCosto() { return costo; }

    // Setters
    public void setIdInstalacion(int idInstalacion) { this.idInstalacion = idInstalacion; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setFoto(byte[] foto) { this.foto = foto; }
    public void setCosto(int costo) { this.costo = costo; }
}