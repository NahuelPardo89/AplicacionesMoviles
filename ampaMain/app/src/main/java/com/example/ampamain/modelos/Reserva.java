package com.example.ampamain.modelos;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Reserva")
public class Reserva {
    @PrimaryKey
    @NonNull
    private int idReserva;
    private int instalacionIdInstalacion;
    @NonNull
    private String userProfileUserId; // UID de Firebase

    public Reserva(int idReserva, int instalacionIdInstalacion, @NonNull String userProfileUserId) {
        this.idReserva = idReserva;
        this.instalacionIdInstalacion = instalacionIdInstalacion;
        this.userProfileUserId = userProfileUserId;
    }

    // Getters
    public int getIdReserva() { return idReserva; }
    public int getInstalacionIdInstalacion() { return instalacionIdInstalacion; }
    public String getUserProfileUserId() { return userProfileUserId; }

    // Setters
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }
    public void setInstalacionIdInstalacion(int instalacionIdInstalacion) { this.instalacionIdInstalacion = instalacionIdInstalacion; }
    public void setUserProfileUserId(String userProfileUserId) { this.userProfileUserId = userProfileUserId; }
}
