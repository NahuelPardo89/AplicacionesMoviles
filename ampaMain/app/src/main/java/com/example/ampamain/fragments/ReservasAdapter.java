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
import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.modelos.Instalacion;
import com.example.ampamain.modelos.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservasAdapter extends RecyclerView.Adapter<ReservasAdapter.ReservaViewHolder> {

    private List<Reserva> reservasList = new ArrayList<>();
    private final Context context;
    private final OnReservaListener listener;

    public interface OnReservaListener {
        void onDeleteReserva(Reserva reserva);
    }

    public ReservasAdapter(Context context, OnReservaListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_reservas_lista, parent, false);
        return new ReservaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
        Reserva reserva = reservasList.get(position);

        // Consultar la instalación asociada con esta reserva
        Instalacion instalacion = AppDatabase.getInstance(context).instalacionDao().getInstalacionById(reserva.getIdInstalacion());
        if (instalacion != null) {
            holder.nombreInstalacionTextView.setText(instalacion.getNombre());
        }
        holder.fechaTextView.setText(reserva.getFecha());
        holder.horaTextView.setText(reserva.getHora());

        holder.cancelarButton.setOnClickListener(v -> {
            listener.onDeleteReserva(reserva);
        });
    }

    @Override
    public int getItemCount() {
        return reservasList.size();
    }

    public void setReservasList(List<Reserva> newList) {
        this.reservasList.clear();
        this.reservasList.addAll(newList);
        notifyDataSetChanged();
    }

    static class ReservaViewHolder extends RecyclerView.ViewHolder {
        final TextView nombreInstalacionTextView;
        final TextView fechaTextView;
        final TextView horaTextView;
        final Button cancelarButton;

        ReservaViewHolder(View itemView) {
            super(itemView);
            nombreInstalacionTextView = itemView.findViewById(R.id.instalacion_nombre);
            fechaTextView = itemView.findViewById(R.id.reserva_fecha);
            horaTextView = itemView.findViewById(R.id.reserva_hora);
            cancelarButton = itemView.findViewById(R.id.cancel_reserva_button);
        }
    }
}