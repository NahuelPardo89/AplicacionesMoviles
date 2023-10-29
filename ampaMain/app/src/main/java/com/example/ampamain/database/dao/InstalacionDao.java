package com.example.ampamain.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ampamain.modelos.Instalacion;

import java.util.List;

@Dao
public interface InstalacionDao {
    @Insert
    long insert(Instalacion instalacion);

    @Update
    void update(Instalacion instalacion);

    @Delete
    void delete(Instalacion instalacion);

    @Query("SELECT * FROM instalacion")
    LiveData<List<Instalacion>> getAllInstalaciones();

    @Query("SELECT * FROM instalacion WHERE idInstalacion = :id")
    Instalacion getInstalacionById(long id);
}