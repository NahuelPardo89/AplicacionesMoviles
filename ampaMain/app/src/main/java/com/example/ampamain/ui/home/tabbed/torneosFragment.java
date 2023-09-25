package com.example.ampamain.ui.home.tabbed;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ampamain.R;
import com.example.ampamain.ui.home.Torneos;
import com.example.ampamain.ui.home.TournamentsAdapter;

import java.util.ArrayList;
import java.util.List;

public class torneosFragment extends Fragment {

    public torneosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_torneos, container, false);

        // Create a list of predefined tournaments
        List<Torneos> torneosList = new ArrayList<>();
        torneosList.add(new Torneos("Torneo 1", "Fecha: 21/09/2023, inscripciones hasta: 20/10/2023, Costo por equipo:$20000, para mas informacion contáctenos", R.drawable.torneo1));
        torneosList.add(new Torneos("Torneo 2", "Fecha: 21/09/2023, inscripciones hasta: 20/10/2023, Costo por equipo:$20000, para mas informacion contáctenos", R.drawable.torneo2));
        torneosList.add(new Torneos("Torneo 3", "Fecha: 21/09/2023, inscripciones hasta: 20/10/2023, Costo por equipo:$20000, para mas informacion contáctenos", R.drawable.torneo3));

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.tournaments_recycler_view);
        TournamentsAdapter adapter = new TournamentsAdapter(getContext(), torneosList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }
}
