package com.example.ampamain.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ampamain.modelos.InscripcionTorneo;

import java.util.List;

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
}