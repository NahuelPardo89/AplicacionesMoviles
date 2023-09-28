package com.example.ampamain.ui.home;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ampamain.R;

import java.util.List;

public class TournamentsAdapter extends RecyclerView.Adapter<TournamentsAdapter.TournamentViewHolder> {

    private final List<Torneos> tournamentList;
    private final Context context;

    private final OnTournamentClickListener listener;
    public TournamentsAdapter(Context context, List<Torneos> tournamentList,OnTournamentClickListener listener) {
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
        holder.titleTextView.setText(tournament.getTitle());
        holder.descriptionTextView.setText(tournament.getDescription());
        holder.imageView.setImageResource(tournament.getImageResource());
        holder.inscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTournamentClick(tournament);
                }
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