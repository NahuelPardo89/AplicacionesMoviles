package com.example.ampamain;

import android.app.Application;
import android.content.Context;

import com.example.ampamain.database.DatabaseClient;

public class MyApplication extends Application {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseClient.getInstance(this); // Esto inicializará tu base de datos
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}