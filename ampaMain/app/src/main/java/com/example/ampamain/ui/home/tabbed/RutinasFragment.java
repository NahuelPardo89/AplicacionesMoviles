package com.example.ampamain.ui.home.tabbed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ampamain.R;
import com.example.ampamain.ui.home.Rutinas;
import com.example.ampamain.ui.home.RutinasAdapter;

import java.util.ArrayList;
import java.util.List;


public class RutinasFragment extends Fragment {

    public RutinasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rutinas, container, false);

        // Crear una lista de rutinas
        List<Rutinas> rutinasList = new ArrayList<>();
        // Añadir algunas rutinas de ejemplo a la lista
        rutinasList.add(new Rutinas("https://firebasestorage.googleapis.com/v0/b/ampa-api.appspot.com/o/rutina1.mp4?alt=media&token=05975be2-c2e6-4a85-8859-e08918110c1c", "Título 1", "Descripción 1"));
        rutinasList.add(new Rutinas("https://firebasestorage.googleapis.com/v0/b/ampa-api.appspot.com/o/rutina1.mp4?alt=media&token=05975be2-c2e6-4a85-8859-e08918110c1c", "Título 1", "Descripción 1"));
        rutinasList.add(new Rutinas("https://firebasestorage.googleapis.com/v0/b/ampa-api.appspot.com/o/rutina1.mp4?alt=media&token=05975be2-c2e6-4a85-8859-e08918110c1c", "Título 1", "Descripción 1"));

        // Configurar el RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rutinas_recycler_view);
        RutinasAdapter adapter = new RutinasAdapter(getContext(), rutinasList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }
}