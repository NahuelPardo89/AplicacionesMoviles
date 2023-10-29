package com.example.ampamain.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ampamain.modelos.InscripcionTorneo;

import java.util.List;


// CRUD INSCRIPCIONES
@Dao
public interface InscripcionTorneoDao {
    @Insert
    long insert(InscripcionTorneo inscripcion);

    @Update
    void update(InscripcionTorneo inscripcion);

    @Delete
    void delete(InscripcionTorneo inscripcion);

    @Query("SELECT * FROM inscripciontorneo")
    List<InscripcionTorneo> getAllInscripciones();

    @Query("SELECT * FROM inscripciontorneo WHERE idInscripcionTorneo = :id")
    InscripcionTorneo getInscripcionById(long id);

    @Query("SELECT torneosIdTorneos FROM inscripciontorneo WHERE UserId = :userId")
    List<Long> getInscripcionByUser(String userId);

    @Query("SELECT * FROM inscripciontorneo WHERE UserId = :userId")
    LiveData<List<InscripcionTorneo>> getInscripcionesByUserFull(String userId);
}