package com.example.ampamain.fragments.home;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ampamain.R;
import com.example.ampamain.modelos.Torneos;

import java.util.ArrayList;
import java.util.List;

public class TournamentsAdapter extends RecyclerView.Adapter<TournamentsAdapter.TournamentViewHolder> {

    private List<Torneos> torneosList = new ArrayList<>();
    private final Context context;

    public TournamentsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TournamentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_torneos, parent, false);
        return new TournamentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TournamentViewHolder holder, int position) {
        Torneos torneo = torneosList.get(position);
        holder.titleTextView.setText(torneo.getTitulo());
        holder.descriptionTextView.setText(torneo.getDescripcion());

        // Convertir byte[] a Bitmap y establecer en ImageView
        byte[] image = torneo.getImg();
        if (image != null && image.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.imageView.setImageBitmap(bitmap);
        } else {
            holder.imageView.setImageResource(R.drawable.canchafutbol);  // Establece una imagen predeterminada si no hay imagen.
        }

        holder.inscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes manejar el evento de click del botón.
            }
        });
    }

    @Override
    public int getItemCount() {
        return torneosList.size();
    }

    public void setTorneosList(List<Torneos> newTorneosList) {
        this.torneosList.clear();
        this.torneosList.addAll(newTorneosList);
        notifyDataSetChanged();
    }

    static class TournamentViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView titleTextView;
        final TextView descriptionTextView;
        final Button inscribeButton;

        TournamentViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tournament_image);
            titleTextView = itemView.findViewById(R.id.tournament_title);
            descriptionTextView = itemView.findViewById(R.id.tournament_description);
            inscribeButton = itemView.findViewById(R.id.inscribe_button);
        }
    }
}
