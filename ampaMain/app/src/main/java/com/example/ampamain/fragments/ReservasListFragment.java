package com.example.ampamain.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ampamain.R;
import com.example.ampamain.fragments.ReservasAdapter;
import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.modelos.Reserva;
import com.google.android.material.snackbar.Snackbar;

public class ReservasListFragment extends Fragment implements ReservasAdapter.OnReservaListener {

    private ReservaViewModel reservaViewModel;
    private ReservasAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_reservas, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.reservas_recycler_view);
        adapter = new ReservasAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        reservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);
        reservaViewModel.getReservas().observe(getViewLifecycleOwner(), reservas -> {
            if (reservas != null) {
                adapter.setReservasList(reservas);
            }
        });

        return view;
    }

    @Override
    public void onDeleteReserva(Reserva reserva) {
        new Thread(() -> {
            AppDatabase.getInstance(getContext()).reservaDao().delete(reserva);
            getActivity().runOnUiThread(() -> {
                Snackbar.make(getView(), "Reserva cancelada con éxito", Snackbar.LENGTH_SHORT).show();
            });
        }).start();
    }
}