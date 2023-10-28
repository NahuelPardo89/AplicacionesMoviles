package com.example.ampamain.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ampamain.R;
import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.database.dao.TorneosDao;
import com.example.ampamain.fragments.home.tabbed.TorneoViewModel;
import com.example.ampamain.modelos.InscripcionTorneo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class InscripcionTorneosFragment extends Fragment implements InscripcionesAdapter.OnInscripcionListener {

    private InscripcionTorneosViewModel inscripcionesViewModel;
    private TorneoViewModel torneosViewModel;
    private InscripcionesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inscripcion_torneos, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.inscripciones_recycler_view);
        TorneosDao torneosDao = AppDatabase.getInstance(getContext()).torneosDao();
        adapter = new InscripcionesAdapter(getContext(), this, torneosDao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar ViewModels
        inscripcionesViewModel = new ViewModelProvider(this).get(InscripcionTorneosViewModel.class);
        torneosViewModel = new ViewModelProvider(requireActivity()).get(TorneoViewModel.class); // Inicializa TorneoViewModel

        // Observar cambios en la lista de inscripciones y actualizar la UI
        inscripcionesViewModel.getInscripciones().observe(getViewLifecycleOwner(), inscripciones -> {
            if (inscripciones != null) {
                adapter.setInscripcionesList(inscripciones);
            }
        });

        return view;
    }

    @Override
    public void onDeleteInscripcion(InscripcionTorneo inscripcion) {
        new Thread(() -> {
            AppDatabase.getInstance(getContext()).inscripcionTorneoDao().delete(inscripcion);

            // Actualiza la lista de inscripciones en TorneoViewModel
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                List<Long> inscripciones = AppDatabase.getInstance(getContext()).inscripcionTorneoDao().getInscripcionByUser(user.getUid());
                torneosViewModel.updateTorneosInscritos(inscripciones);
            }
        }).start();
    }
}
