package com.example.ampamain.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Versión de la base de datos
    private static final int DATABASE_VERSION = 1;

    // Nombre de la base de datos
    private static final String DATABASE_NAME = "ampamain_db";

    // Nombres de las tablas
    public static final String TABLE_TORNEOS = "Torneos";
    public static final String TABLE_INSCRIPCION_TORNEO = "InscripcionTorneo";
    public static final String TABLE_INSTALACION = "Instalacion";
    public static final String TABLE_RESERVA = "Reserva";
    public static final String TABLE_RUTINA = "Rutina";

    // Creación de las tablas
    private static final String CREATE_TABLE_TORNEOS = "CREATE TABLE "
            + TABLE_TORNEOS + "(idTorneos INTEGER PRIMARY KEY, titulo TEXT, descripcion TEXT, costo INTEGER, img BLOB)";

    private static final String CREATE_TABLE_INSCRIPCION_TORNEO = "CREATE TABLE "
            + TABLE_INSCRIPCION_TORNEO + "(idInscripcionTorneo INTEGER PRIMARY KEY, Torneos_idTorneos INTEGER, UserProfile_User_idUser TEXT, "
            + "FOREIGN KEY(Torneos_idTorneos) REFERENCES " + TABLE_TORNEOS + "(idTorneos))"; // Aquí hemos añadido la llave externa

    private static final String CREATE_TABLE_INSTALACION = "CREATE TABLE "
            + TABLE_INSTALACION + "(idInstalacion INTEGER PRIMARY KEY, nombre TEXT, foto BLOB, costo INTEGER)";

    private static final String CREATE_TABLE_RESERVA = "CREATE TABLE "
            + TABLE_RESERVA + "(idReserva INTEGER PRIMARY KEY, Instalacion_idInstalacion INTEGER, UserProfile_User_idUser TEXT, "
            + "FOREIGN KEY(Instalacion_idInstalacion) REFERENCES " + TABLE_INSTALACION + "(idInstalacion))"; // Aquí hemos añadido la llave externa

    private static final String CREATE_TABLE_RUTINA = "CREATE TABLE "
            + TABLE_RUTINA + "(idRutina INTEGER PRIMARY KEY, nombre TEXT, descripcion TEXT, imgPreview BLOB, videourl TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TORNEOS);
        db.execSQL(CREATE_TABLE_INSCRIPCION_TORNEO);
        db.execSQL(CREATE_TABLE_INSTALACION);
        db.execSQL(CREATE_TABLE_RESERVA);
        db.execSQL(CREATE_TABLE_RUTINA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TORNEOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSCRIPCION_TORNEO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTALACION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RUTINA);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
