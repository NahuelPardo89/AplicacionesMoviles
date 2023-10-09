package com.example.ampamain.modelos;

public class Instalacion {
    private String nombre;
    private String descripcion;
    private int imagenRecurso;
    private String costo;

    public Instalacion(String nombre, String descripcion, int imagenRecurso, String costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenRecurso = imagenRecurso;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagenRecurso() {
        return imagenRecurso;
    }

    public String getCosto() {
        return costo;
    }
}