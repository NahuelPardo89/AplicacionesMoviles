package com.example.ampamain.modelos;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Reserva")
public class Reserva {
    @PrimaryKey(autoGenerate = true)
    private Long idReserva;
    private int IdInstalacion;
    @NonNull
    private String UserId; // UID de Firebase
    private String fecha;
    private String hora;


    public Reserva() {}

    public Reserva(int IdInstalacion, @NonNull String UserId, String fecha, String hora) {
        this.IdInstalacion = IdInstalacion;
        this.UserId = UserId;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters
    public Long getIdReserva() { return idReserva; }
    public int getIdInstalacion() { return IdInstalacion; }
    public String getUserId() { return UserId; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }

    // Setters
    public void setIdReserva(Long idReserva) { this.idReserva = idReserva; }
    public void setIdInstalacion(int IdInstalacion) { this.IdInstalacion = IdInstalacion; }
    public void setUserId(String UserId) { this.UserId = UserId; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setHora(String hora) { this.hora = hora; }
}