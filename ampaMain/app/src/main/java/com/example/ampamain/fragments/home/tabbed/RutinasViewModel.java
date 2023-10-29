package com.example.ampamain.fragments.home.tabbed;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.ampamain.MyApplication;
import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.modelos.Rutinas;
import java.util.List;

public class RutinasViewModel extends ViewModel {
    private final LiveData<List<Rutinas>> allRutinas;
    private final AppDatabase db;

    public RutinasViewModel() {
        db = AppDatabase.getInstance(MyApplication.getContext());
        allRutinas = db.rutinaDao().getAllRutinas();
    }

    public LiveData<List<Rutinas>> getAllRutinas() {
        return allRutinas;
    }
}