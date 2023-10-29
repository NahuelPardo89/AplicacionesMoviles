package com.example.ampamain.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ampamain.modelos.Reserva;

import java.util.List;

@Dao
public interface ReservaDao {
    @Insert
    long insert(Reserva reserva);

    @Update
    void update(Reserva reserva);

    @Delete
    void delete(Reserva reserva);

    @Query("SELECT * FROM reserva")
    List<Reserva> getAllReservas();

    @Query("SELECT * FROM reserva WHERE idReserva = :id")
    Reserva getReservaById(long id);

    @Query("SELECT * FROM Reserva WHERE UserId = :userId")
    LiveData<List<Reserva>> getReservasByUser(String userId);
}
