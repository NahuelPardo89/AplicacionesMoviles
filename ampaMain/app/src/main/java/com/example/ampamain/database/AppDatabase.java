package com.example.ampamain.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ampamain.database.dao.InscripcionTorneoDao;
import com.example.ampamain.database.dao.InstalacionDao;
import com.example.ampamain.database.dao.ReservaDao;
import com.example.ampamain.database.dao.RutinaDao;
import com.example.ampamain.database.dao.TorneosDao;
import com.example.ampamain.modelos.InscripcionTorneo;
import com.example.ampamain.modelos.Instalacion;
import com.example.ampamain.modelos.Reserva;
import com.example.ampamain.modelos.Rutinas;
import com.example.ampamain.modelos.Torneos;

@Database(entities = {Torneos.class, InscripcionTorneo.class, Instalacion.class, Reserva.class, Rutinas.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TorneosDao torneosDao();
    public abstract InscripcionTorneoDao inscripcionTorneoDao();
    public abstract InstalacionDao instalacionDao();
    public abstract ReservaDao reservaDao();
    public abstract RutinaDao rutinaDao();
}