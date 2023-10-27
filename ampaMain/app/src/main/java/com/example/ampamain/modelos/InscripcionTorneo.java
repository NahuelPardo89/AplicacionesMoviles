package com.example.ampamain.modelos;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "InscripcionTorneo")
public class InscripcionTorneo {
    @PrimaryKey(autoGenerate = true)
    private Long idInscripcionTorneo;
    private int torneosIdTorneos;
    @NonNull
    private String userProfileUserId; // UID de Firebase

    public InscripcionTorneo( int torneosIdTorneos, @NonNull String userProfileUserId) {
        this.torneosIdTorneos = torneosIdTorneos;
        this.userProfileUserId = userProfileUserId;
    }

    // Getters
    public Long getIdInscripcionTorneo() { return idInscripcionTorneo; }
    public int getTorneosIdTorneos() { return torneosIdTorneos; }
    public String getUserProfileUserId() { return userProfileUserId; }

    // Setters
    public void setIdInscripcionTorneo(Long idInscripcionTorneo) { this.idInscripcionTorneo = idInscripcionTorneo; }
    public void setTorneosIdTorneos(int torneosIdTorneos) { this.torneosIdTorneos = torneosIdTorneos; }
    public void setUserProfileUserId(String userProfileUserId) { this.userProfileUserId = userProfileUserId; }
}