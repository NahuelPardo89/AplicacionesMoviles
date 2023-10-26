package com.example.ampamain.fragments.home;

import android.content.Context;
import android.content.Intent;
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
import com.bumptech.glide.Glide;
import com.example.ampamain.R;
import com.example.ampamain.VideoPlayerActivity;
import com.example.ampamain.modelos.Rutinas;

import java.util.ArrayList;
import java.util.List;

public class RutinasAdapter extends RecyclerView.Adapter<RutinasAdapter.RutinaViewHolder> {

    private List<Rutinas> rutinasList = new ArrayList<>();
    private final Context context;

    public RutinasAdapter(Context context, List<Rutinas> rutinasList) {
        this.context = context;

    }

    @NonNull
    @Override
    public RutinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_rutinas, parent, false);
        return new RutinaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinaViewHolder holder, int position) {
        Rutinas rutina = rutinasList.get(position);
        holder.titleTextView.setText(rutina.getNombre());
        holder.descriptionTextView.setText(rutina.getDescripcion());

        // Convertir byte[] a Bitmap y establecer en ImageView
        byte[] image = rutina.getImgPreview();
        if (image != null && image.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            Glide.with(context).load(bitmap).into(holder.previewImageView);
        } else {
            Glide.with(context).load(R.drawable.gimnasio).into(holder.previewImageView);  // Cargar una imagen predeterminada si no hay imagen.
        }

        // Configurar el escuchador del botón Reproducir
        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("videoUrl", rutina.getVideourl());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return rutinasList.size();
    }

    public void setRutinasList(List<Rutinas> newRutinasList) {
        this.rutinasList.clear();
        this.rutinasList.addAll(newRutinasList);
        notifyDataSetChanged();
    }

    static class RutinaViewHolder extends RecyclerView.ViewHolder {
        final ImageView previewImageView;
        final Button playButton;
        final TextView titleTextView;
        final TextView descriptionTextView;

        RutinaViewHolder(View itemView) {
            super(itemView);
            previewImageView = itemView.findViewById(R.id.rutina_preview_image);
            playButton = itemView.findViewById(R.id.rutina_play_button);
            titleTextView = itemView.findViewById(R.id.rutina_title);
            descriptionTextView = itemView.findViewById(R.id.rutina_description);
        }
    }
}
