package com.example.ampamain;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.database.dao.RutinaDao;
import com.example.ampamain.database.dao.TorneosDao;
import com.example.ampamain.modelos.Rutinas;
import com.example.ampamain.modelos.Torneos;

public class MyApplication extends Application {
    private static MyApplication instance;

    public static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Thread(() -> {
                TorneosDao torneosDao = AppDatabase.getInstance(getContext()).torneosDao();
                torneosDao.insert(new Torneos(1,"Torneo 1", "Fecha: 21/09/2023, inscripciones hasta: 20/10/2023, Costo por equipo:$20000, para más información contáctenos", 100,null));
                torneosDao.insert(new Torneos(2,"Torneo 2", "Fecha: 21/09/2023, inscripciones hasta: 20/10/2023, Costo por equipo:$20000, para más información contáctenos", 200,null));
                torneosDao.insert(new Torneos(3,"Torneo 3", "Fecha: 21/09/2023, inscripciones hasta: 20/10/2023, Costo por equipo:$20000, para más información contáctenos", 300,null));
                // Inserta Rutinas
                RutinaDao rutinaDao = AppDatabase.getInstance(getContext()).rutinaDao();
                rutinaDao.insert(new Rutinas(1,"Rutina 1", "Descripción de la rutina 1", /* Aquí va la imagen en formato byte[] */ null, "url_video_1"));
                rutinaDao.insert(new Rutinas(2,"Rutina 2", "Descripción de la rutina 2", /* Aquí va la imagen en formato byte[] */ null, "url_video_2"));
                rutinaDao.insert(new Rutinas(3,"Rutina 3", "Descripción de la rutina 3", /* Aquí va la imagen en formato byte[] */ null, "url_video_3"));
            }).start();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppDatabase.getInstance(this);

    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
