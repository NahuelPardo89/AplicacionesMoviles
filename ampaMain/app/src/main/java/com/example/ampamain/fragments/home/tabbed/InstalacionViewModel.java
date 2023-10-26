package com.example.ampamain.fragments.home.tabbed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.ampamain.MyApplication;
import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.modelos.Instalacion;

import java.util.List;

public class InstalacionViewModel extends ViewModel {
    private final LiveData<List<Instalacion>> instalaciones;
    private final AppDatabase db;

    public InstalacionViewModel() {
        db = Room.databaseBuilder(MyApplication.getContext(), AppDatabase.class, "ampamain_db").build();
        instalaciones = db.instalacionDao().getAllInstalaciones();
    }

    public LiveData<List<Instalacion>> getInstalaciones() {
        return instalaciones;
    }
}