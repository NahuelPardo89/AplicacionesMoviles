package com.example.ampamain.modelos;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Torneos")
public class Torneos {
    @PrimaryKey(autoGenerate = true)
    private Long idTorneos;
    private String titulo;
    private String descripcion;
    private int costo;
    private byte[] img; // Representando LONGBLOB como byte[]

    public Torneos( String titulo, String descripcion, int costo, byte[] img) {
        this.idTorneos = idTorneos;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.costo = costo;
        this.img = img;
    }

    // Getters
    public Long getIdTorneos() { return idTorneos; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public int getCosto() { return costo; }
    public byte[] getImg() { return img; }

    // Setters
    public void setIdTorneos(Long idTorneos) { this.idTorneos = idTorneos; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setCosto(int costo) { this.costo = costo; }
    public void setImg(byte[] img) { this.img = img; }
}
