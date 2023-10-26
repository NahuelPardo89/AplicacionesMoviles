package com.example.ampamain.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ampamain.modelos.Rutinas;

import java.util.List;

@Dao
public interface RutinaDao {
    @Insert
    long insert(Rutinas rutina);

    @Update
    void update(Rutinas rutina);

    @Delete
    void delete(Rutinas rutina);

    @Query("SELECT * FROM rutinas")
    LiveData<List<Rutinas>> getAllRutinas();

    @Query("SELECT * FROM rutinas WHERE idRutina = :id")
    LiveData<Rutinas> getRutinaById(long id);
}
