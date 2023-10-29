package com.example.ampamain.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ampamain.MyApplication;
import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.modelos.Reserva;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ReservaViewModel extends ViewModel {
    private final LiveData<List<Reserva>> reservas;
    private final AppDatabase db;

    public ReservaViewModel() {
        db = AppDatabase.getInstance(MyApplication.getContext());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            reservas = db.reservaDao().getReservasByUser(user.getUid());
        } else {
            reservas = new MutableLiveData<>(new ArrayList<>());
        }
    }

    public LiveData<List<Reserva>> getReservas() {
        return reservas;
    }
}