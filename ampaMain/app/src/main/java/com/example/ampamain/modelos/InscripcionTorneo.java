package com.example.ampamain.modelos;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "InscripcionTorneo")
public class InscripcionTorneo {
    @PrimaryKey(autoGenerate = true)
    private Long idInscripcionTorneo;
    private Long torneosIdTorneos;
    @NonNull
    private String UserId; // UID de Firebase

    public InscripcionTorneo(Long torneosIdTorneos, @NonNull String UserId) {
        this.torneosIdTorneos = torneosIdTorneos;
        this.UserId = UserId;
    }

    // Getters
    public Long getIdInscripcionTorneo() { return idInscripcionTorneo; }
    public Long getTorneosIdTorneos() { return torneosIdTorneos; }
    public String getUserId() { return UserId; }

    // Setters
    public void setIdInscripcionTorneo(Long idInscripcionTorneo) { this.idInscripcionTorneo = idInscripcionTorneo; }
    public void setTorneosIdTorneos(Long torneosIdTorneos) { this.torneosIdTorneos = torneosIdTorneos; }
    public void setUserId(String UserId) { this.UserId = UserId; }
}