package com.example.ampamain.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ampamain.modelos.Rutinas;
import com.example.ampamain.modelos.Torneos;

import java.util.List;

@Dao
public interface TorneosDao {
    @Insert
    long insert(Torneos torneo);

    @Update
    void update(Torneos torneo);

    @Delete
    void delete(Torneos torneo);

    @Query("SELECT * FROM torneos")
    LiveData<List<Torneos>> getAllTorneos();

    @Query("SELECT * FROM torneos WHERE idTorneos = :id")
    Torneos getTorneoById(long id);

}