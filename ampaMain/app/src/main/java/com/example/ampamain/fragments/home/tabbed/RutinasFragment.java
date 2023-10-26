package com.example.ampamain.fragments.home.tabbed;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ampamain.R;
import com.example.ampamain.fragments.home.RutinasAdapter;

import java.util.ArrayList;

public class RutinasFragment extends Fragment {

    private RutinasViewModel rutinaViewModel;

    public RutinasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rutinas, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rutinas_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        RutinasAdapter adapter = new RutinasAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Inicializar el ViewModel
        rutinaViewModel = new ViewModelProvider(this).get(RutinasViewModel.class);

        // Observar los cambios en la lista de rutinas
        rutinaViewModel.getRutinas().observe(getViewLifecycleOwner(), rutinas -> {
            // Actualizar la interfaz de usuario cuando la lista de rutinas cambie
            adapter.setRutinasList(rutinas);
        });

        return view;
    }
}
}