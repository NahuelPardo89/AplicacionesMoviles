package com.example.ampamain.modelos;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Reserva")
public class Reserva {
    @PrimaryKey(autoGenerate = true)
    private Long idReserva;
    private int instalacionIdInstalacion;
    @NonNull
    private String userProfileUserId; // UID de Firebase

    public Reserva( int instalacionIdInstalacion, @NonNull String userProfileUserId) {

        this.instalacionIdInstalacion = instalacionIdInstalacion;
        this.userProfileUserId = userProfileUserId;
    }

    // Getters
    public Long getIdReserva() { return idReserva; }
    public int getInstalacionIdInstalacion() { return instalacionIdInstalacion; }
    public String getUserProfileUserId() { return userProfileUserId; }

    // Setters
    public void setIdReserva(Long idReserva) { this.idReserva = idReserva; }
    public void setInstalacionIdInstalacion(int instalacionIdInstalacion) { this.instalacionIdInstalacion = instalacionIdInstalacion; }
    public void setUserProfileUserId(String userProfileUserId) { this.userProfileUserId = userProfileUserId; }
}
