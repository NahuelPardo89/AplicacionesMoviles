package com.example.ampamain.database;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
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
import com.example.ampamain.MyApplication; // Importante: Importar la clase MyApplication

@Database(entities = {Torneos.class, InscripcionTorneo.class, Instalacion.class, Reserva.class, Rutinas.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract TorneosDao torneosDao();
    public abstract InscripcionTorneoDao inscripcionTorneoDao();
    public abstract InstalacionDao instalacionDao();
    public abstract ReservaDao reservaDao();
    public abstract RutinaDao rutinaDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "ampa_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(MyApplication.callback)  // Añadir el callback aquí
                    .allowMainThreadQueries()  // Permitir consultas en el hilo principal (Solo para pruebas)
                    .build();
        }
        return INSTANCE;
    }
}
