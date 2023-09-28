package com.example.ampamain.ui.home.tabbed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ampamain.R;
import com.example.ampamain.ui.home.Instalacion;
import com.example.ampamain.ui.home.InstalacionesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReservasFragment extends Fragment {

    public ReservasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservas, container, false);

        // Crear una lista de instalaciones
        List<Instalacion> instalacionList = new ArrayList<>();
        // Añadir algunas instalaciones de ejemplo a la lista
        instalacionList.add(new Instalacion("Cancha de futbol ", "Descripción 1", R.drawable.canchafutbol, "100"));
        instalacionList.add(new Instalacion("cancha de paddle", "Descripción 2", R.drawable.canchapaddle, "200"));
        instalacionList.add(new Instalacion("Gimnansio", "Descripción 3", R.drawable.gimnasio, "300"));

        // Configurar el RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.reservas_recycler_view);
        InstalacionesAdapter adapter = new InstalacionesAdapter(getContext(), instalacionList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }
}
