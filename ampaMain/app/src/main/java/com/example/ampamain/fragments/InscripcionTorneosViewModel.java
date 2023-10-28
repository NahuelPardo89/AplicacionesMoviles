package com.example.ampamain.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ampamain.MyApplication;
import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.modelos.InscripcionTorneo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class InscripcionTorneosViewModel extends ViewModel {
    private final LiveData<List<InscripcionTorneo>> inscripciones;
    private final AppDatabase db;

    public InscripcionTorneosViewModel() {
        db = AppDatabase.getInstance(MyApplication.getContext());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            inscripciones = db.inscripcionTorneoDao().getInscripcionesByUserFull(user.getUid());
        } else {
            inscripciones = new MutableLiveData<>(new ArrayList<>());
        }
    }

    public LiveData<List<InscripcionTorneo>> getInscripciones() {
        return inscripciones;
    }
}