package com.example.ampamain.fragments.home.tabbed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ampamain.R;
import com.example.ampamain.modelos.Instalacion;
import com.example.ampamain.fragments.home.InstalacionesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReservasFragment extends Fragment {

    private RecyclerView recyclerView;
    private InstalacionesAdapter adapter;
    private InstalacionViewModel viewModel;

    public ReservasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservas, container, false);

        // Configurar el RecyclerView
        recyclerView = view.findViewById(R.id.reservas_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Crear el ViewModel
        viewModel = new ViewModelProvider(this).get(InstalacionViewModel.class);
        viewModel.getInstalaciones().observe(getViewLifecycleOwner(), new Observer<List<Instalacion>>() {
            @Override
            public void onChanged(List<Instalacion> instalaciones) {
                adapter = new InstalacionesAdapter(getContext(), instalaciones);
                recyclerView.setAdapter(adapter);
            }
        });

        return view;
    }
}