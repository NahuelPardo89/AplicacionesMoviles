package com.example.ampamain;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.database.dao.InstalacionDao;
import com.example.ampamain.database.dao.RutinaDao;
import com.example.ampamain.database.dao.TorneosDao;
import com.example.ampamain.modelos.Instalacion;
import com.example.ampamain.modelos.Rutinas;
import com.example.ampamain.modelos.Torneos;

import java.io.ByteArrayOutputStream;

public class MyApplication extends Application {
    private static MyApplication instance;

    public static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Thread(() -> {
                // Inicializando db tabla torneos
                TorneosDao torneosDao = AppDatabase.getInstance(getContext()).torneosDao();
                byte[] imageTorneo1 = convertDrawableToByteArray(getContext(), R.drawable.torneofutbol);
                byte[] imageTorneo2 = convertDrawableToByteArray(getContext(), R.drawable.torneoninos);
                byte[] imageTorneo3 = convertDrawableToByteArray(getContext(), R.drawable.canchapaddle);
                torneosDao.insert(new Torneos("Torneo de Fútbol Senior", "Fecha: 21/11/2023, inscripciones hasta: 20/12/2023, Costo por equipo:$20000, para más información contáctenos", 20000,imageTorneo1));
                torneosDao.insert(new Torneos("Torneo de Fútbol Niños", "Fecha: 21/11/2023, inscripciones hasta: 20/12/2023, Costo por equipo:$20000, para más información contáctenos", 20000,imageTorneo2));
                torneosDao.insert(new Torneos("Torneo Paddle", "Fecha: 21/11/2023, inscripciones hasta: 20/12/2023, Costo por equipo:$5000, para más información contáctenos", 5000,imageTorneo3));
                // Inicializando db tabla Rutinas
                RutinaDao rutinaDao = AppDatabase.getInstance(getContext()).rutinaDao();

                byte[] imageRutina1 = convertDrawableToByteArray(getContext(), R.drawable.torneofutbol);
                byte[] imageRutina2 = convertDrawableToByteArray(getContext(), R.drawable.torneoninos);
                byte[] imageRutina3 = convertDrawableToByteArray(getContext(), R.drawable.canchapaddle);
                rutinaDao.insert(new Rutinas("Rutina Brazos", "Cómo conseguir resultados sorprendentes con series gigantes", null, "https://firebasestorage.googleapis.com/v0/b/ampa-api.appspot.com/o/rutinabrazos.mp4?alt=media&token=469a8615-61ea-4bef-901f-c15ba070dc0b"));
                rutinaDao.insert(new Rutinas("Rutina Piernas y gluteos", " poderosa rutina para transformar tus piernas y glúteos",  null, "https://firebasestorage.googleapis.com/v0/b/ampa-api.appspot.com/o/rutinaPiernas.mp4?alt=media&token=61aad5e9-7a12-4a84-a544-13b5a2d5da2c"));
                rutinaDao.insert(new Rutinas("Rutina Pecho", " rutina de ejercicios completa y efectiva para fortalecer y definir tu pecho, bíceps y tríceps",  null, "https://firebasestorage.googleapis.com/v0/b/ampa-api.appspot.com/o/rutinapecho.mp4?alt=media&token=af7606f1-c686-41e7-81bf-0c719417770d"));
                // Inicializando db tabla Instalaciones
                InstalacionDao instalacionDao = AppDatabase.getInstance(getContext()).instalacionDao();

                byte[] imageInstalacion1 = convertDrawableToByteArray(getContext(), R.drawable.canchafutbol);
                byte[] imageInstalacion2 = convertDrawableToByteArray(getContext(), R.drawable.canchabasquet);
                byte[] imageInstalacion3 = convertDrawableToByteArray(getContext(), R.drawable.canchapaddle);
                instalacionDao.insert(new Instalacion(1,"Cancha de Fútbol", "Cancha de césped sintético para partidos de fútbol 7.",imageInstalacion1,22000));
                instalacionDao.insert(new Instalacion(2,"Cancha de Basquet", "Cancha de basquet techada.",imageInstalacion2,20000));
                instalacionDao.insert(new Instalacion(3,"Cancha de Paddle", "Canchas de acrílico.",null,8000));

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

    private static byte[] convertDrawableToByteArray(Context context, int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
