package com.example.ampamain.fragments.home.tabbed;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.ampamain.MyApplication;
import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.modelos.Torneos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class TorneoViewModel extends ViewModel {
    private final LiveData<List<Torneos>> torneos;
    private final MutableLiveData<List<Long>> torneosInscritos = new MutableLiveData<>(new ArrayList<>());
    private final AppDatabase db;

    public TorneoViewModel() {
        db = AppDatabase.getInstance(MyApplication.getContext());
        torneos = db.torneosDao().getAllTorneos();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            new Thread(() -> {
                List<Long> inscripciones = db.inscripcionTorneoDao().getInscripcionByUser(user.getUid());
                torneosInscritos.postValue(inscripciones);
            }).start();
        }
    }

    public LiveData<List<Long>> getTorneosInscritos() {
        return torneosInscritos;
    }

    public void updateTorneosInscritos(List<Long> inscripciones) {
        torneosInscritos.postValue(inscripciones);
    }


    public LiveData<List<Torneos>> getAllTorneos() {
        return torneos;
    }
}
