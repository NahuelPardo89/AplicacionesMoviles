package com.example.ampamain.modelos;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Rutinas")
public class Rutinas {
    @PrimaryKey
    @NonNull
    private int idRutina;
    private String nombre;
    private String descripcion;
    private byte[] imgPreview;
    private String videourl;

    public Rutinas(int idRutina, String nombre, String descripcion, byte[] imgPreview, String videourl) {
        this.idRutina = idRutina;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imgPreview = imgPreview;
        this.videourl = videourl;
    }

    // Getters
    public int getIdRutina() { return idRutina; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public byte[] getImgPreview() { return imgPreview; }
    public String getVideourl() { return videourl; }

    // Setters
    public void setIdRutina(int idRutina) { this.idRutina = idRutina; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setImgPreview(byte[] imgPreview) { this.imgPreview = imgPreview; }
    public void setVideourl(String videourl) { this.videourl = videourl; }
}