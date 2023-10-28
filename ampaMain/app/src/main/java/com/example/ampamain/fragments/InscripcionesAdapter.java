package com.example.ampamain.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ampamain.R;
import com.example.ampamain.database.dao.TorneosDao;
import com.example.ampamain.modelos.InscripcionTorneo;
import com.example.ampamain.modelos.Torneos;

import java.util.ArrayList;
import java.util.List;

public class InscripcionesAdapter extends RecyclerView.Adapter<InscripcionesAdapter.InscripcionViewHolder> {

    private List<InscripcionTorneo> inscripcionesList = new ArrayList<>();

    private final TorneosDao torneosDao;
    private final Context context;
    private final OnInscripcionListener listener;

    public interface OnInscripcionListener {
        void onDeleteInscripcion(InscripcionTorneo inscripcion);
    }

    public InscripcionesAdapter(Context context, OnInscripcionListener listener,TorneosDao torneosDao) {
        this.context = context;
        this.listener = listener;
        this.torneosDao = torneosDao;
    }

    @NonNull
    @Override
    public InscripcionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_inscripciones, parent, false);
        return new InscripcionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InscripcionViewHolder holder, int position) {
        InscripcionTorneo inscripcion = inscripcionesList.get(position);

        // Consultar el torneo asociado con esta inscripción
        Torneos torneo = torneosDao.getTorneoById(inscripcion.getTorneosIdTorneos());
        if (torneo != null) {
            holder.nameTextView.setText(torneo.getTitulo());
            holder.dateTextView.setText(torneo.getDescripcion()); // Puedes cambiar esto por la fecha si es necesario
        }

        holder.deleteButton.setOnClickListener(v -> {
            listener.onDeleteInscripcion(inscripcion);
        });
    }

    @Override
    public int getItemCount() {
        return inscripcionesList.size();
    }

    public void setInscripcionesList(List<InscripcionTorneo> newList) {
        this.inscripcionesList.clear();
        this.inscripcionesList.addAll(newList);
        notifyDataSetChanged();
    }

    static class InscripcionViewHolder extends RecyclerView.ViewHolder {
        final TextView nameTextView;
        final TextView dateTextView;
        final Button deleteButton;

        InscripcionViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.torneo_name_text_view);
            dateTextView = itemView.findViewById(R.id.torneo_date_text_view);
            deleteButton = itemView.findViewById(R.id.delete_inscripcion_button);
        }
    }
}