package com.example.ampamain.ui.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ampamain.R;

import java.util.List;

public class RutinasAdapter extends RecyclerView.Adapter<RutinasAdapter.RutinasViewHolder> {
    private final Context context;
    private final List<Rutinas> rutinasList;

    public RutinasAdapter(Context context, List<Rutinas> rutinasList) {
        this.context = context;
        this.rutinasList = rutinasList;
    }

    @NonNull
    @Override
    public RutinasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_rutinas, parent, false);
        return new RutinasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinasViewHolder holder, int position) {
        Rutinas rutina = rutinasList.get(position);
        holder.videoView.setVideoURI(Uri.parse(rutina.getVideoUrl()));
        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(holder.videoView);
        holder.videoView.setMediaController(mediaController);
        holder.titleTextView.setText(rutina.getTitle());
        holder.descriptionTextView.setText(rutina.getDescription());
        holder.playButton.setOnClickListener(v -> {
            if (!holder.videoView.isPlaying()) {
                holder.videoView.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rutinasList.size();
    }

    static class RutinasViewHolder extends RecyclerView.ViewHolder {
        final VideoView videoView;
        final Button playButton;

        final TextView titleTextView;
        final TextView descriptionTextView;

        RutinasViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.rutina_video_view);
            playButton = itemView.findViewById(R.id.rutina_play_button);
            titleTextView = itemView.findViewById(R.id.rutina_title);
            descriptionTextView = itemView.findViewById(R.id.rutina_description);
        }
    }
}