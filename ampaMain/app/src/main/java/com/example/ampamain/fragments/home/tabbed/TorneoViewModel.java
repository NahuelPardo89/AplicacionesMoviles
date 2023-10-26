package com.example.ampamain.fragments.home.tabbed;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.ampamain.MyApplication;
import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.modelos.Torneos;

import java.util.List;

public class TorneoViewModel extends ViewModel {
    private final LiveData<List<Torneos>> torneos;
    private final AppDatabase db;

    public TorneoViewModel() {
        db = AppDatabase.getInstance(MyApplication.getContext());
        torneos = db.torneosDao().getAllTorneos();
    }

    public LiveData<List<Torneos>> getAllTorneos() {
        return torneos;
    }
}
