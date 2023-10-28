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
import com.example.ampamain.modelos.InscripcionTorneo;

public class InscripcionTorneosFragment extends Fragment implements InscripcionesAdapter.OnInscripcionListener {

    private InscripcionTorneosViewModel inscripcionesViewModel;
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

        // Inicializar ViewModel
        inscripcionesViewModel = new ViewModelProvider(this).get(InscripcionTorneosViewModel.class);

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
        // Aquí, puedes manejar la eliminación de una inscripción, por ejemplo:
        new Thread(() -> {
            AppDatabase.getInstance(getContext()).inscripcionTorneoDao().delete(inscripcion);
            // Actualizar la lista de inscripciones en el ViewModel o directamente en el adaptador
        }).start();
    }
}