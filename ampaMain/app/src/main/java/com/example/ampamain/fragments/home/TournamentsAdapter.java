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

import java.util.List;

public class TournamentsAdapter extends RecyclerView.Adapter<TournamentsAdapter.TournamentViewHolder> {

    private final List<Torneos> tournamentList;
    private final Context context;

    private final OnTournamentClickListener listener;

    public TournamentsAdapter(Context context, List<Torneos> tournamentList, OnTournamentClickListener listener) {
        this.context = context;
        this.tournamentList = tournamentList;
        this.listener = listener;
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
        Torneos tournament = tournamentList.get(position);
        holder.titleTextView.setText(tournament.getTitulo());
        holder.descriptionTextView.setText(tournament.getDescripcion());

        // Convertir byte[] a Bitmap y establecer en ImageView
        byte[] image = tournament.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.imageView.setImageBitmap(bitmap);

        holder.inscribeButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTournamentClick(tournament);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tournamentList.size();
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

    public interface OnTournamentClickListener {
        void onTournamentClick(Torneos torneo);
    }
}