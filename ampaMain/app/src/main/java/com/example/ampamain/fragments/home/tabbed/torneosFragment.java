package com.example.ampamain.fragments.home.tabbed;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.example.ampamain.R;
import com.example.ampamain.fragments.home.TournamentsAdapter;

public class torneosFragment extends Fragment {

    private TorneoViewModel torneosViewModel;

    public torneosFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_torneos, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.tournaments_recycler_view);
        TournamentsAdapter adapter = new TournamentsAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Inicializar ViewModel
        torneosViewModel = new ViewModelProvider(this).get(TorneoViewModel.class);

        // Observar cambios en la lista de torneos y actualizar la UI
        torneosViewModel.getAllTorneos().observe(getViewLifecycleOwner(), torneos -> {
            if (torneos != null) {
                adapter.setTorneosList(torneos);
            }
        });

        // Observar cambios en torneosInscritos y actualizar la UI
        torneosViewModel.getTorneosInscritos().observe(getViewLifecycleOwner(), torneosInscritos -> {
            if (torneosInscritos != null) {
                adapter.setTorneosInscritos(torneosInscritos);
            }
        });

        return view;
    }
}
