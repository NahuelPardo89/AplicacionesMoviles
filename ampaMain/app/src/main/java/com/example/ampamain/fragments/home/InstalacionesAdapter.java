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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ampamain.R;
import com.example.ampamain.database.AppDatabase;
import com.example.ampamain.fragments.home.tabbed.ReservaDialogFragment;
import com.example.ampamain.modelos.Instalacion;
import com.example.ampamain.modelos.Reserva;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class InstalacionesAdapter extends RecyclerView.Adapter<InstalacionesAdapter.InstalacionViewHolder> {

    private final List<Instalacion> instalacionList;
    private final Context context;

    public InstalacionesAdapter(Context context, List<Instalacion> instalacionList) {
        this.context = context;
        this.instalacionList = instalacionList;
    }

    @NonNull
    @Override
    public InstalacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_reservas, parent, false);
        return new InstalacionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InstalacionViewHolder holder, int position) {
        Instalacion instalacion = instalacionList.get(position);
        holder.nombreTextView.setText(instalacion.getNombre());
        holder.descripcionTextView.setText(instalacion.getDescripcion());
        holder.costoTextView.setText("Costo: $" + instalacion.getCosto());

        // Convertir byte[] a Bitmap y establecer en ImageView si la imagen no es null
        byte[] image = instalacion.getFoto();
        if (image != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            holder.imagenImageView.setImageBitmap(bitmap);
        } else {
            // Puedes establecer una imagen por defecto o simplemente dejar el ImageView vacío
            // Por ejemplo, para establecer una imagen por defecto:
            holder.imagenImageView.setImageResource(R.drawable.canchapaddle);
        }

        holder.reservarButton.setOnClickListener(v -> {
            ReservaDialogFragment.ReservaListener listener = (instalacionId, fecha, hora) -> {
                // Crear una nueva reserva
                Reserva nuevaReserva = new Reserva(instalacionId, FirebaseAuth.getInstance().getCurrentUser().getUid(), fecha, hora);

                // Guardar en la base de datos en un hilo secundario
                new Thread(() -> {
                    try {
                        AppDatabase db = AppDatabase.getInstance(context);
                        db.reservaDao().insert(nuevaReserva);

                        // Mostrar Snackbar de éxito en el hilo principal
                        ((FragmentActivity) context).runOnUiThread(() -> {
                            Snackbar.make(v, "Reservado con éxito", Snackbar.LENGTH_SHORT).show();
                        });
                    } catch (Exception e) {
                        // Mostrar Snackbar de error en el hilo principal
                        ((FragmentActivity) context).runOnUiThread(() -> {
                            Snackbar.make(v, "Error al reservar", Snackbar.LENGTH_SHORT).show();
                        });
                    }
                }).start();
            };

            ReservaDialogFragment dialog = new ReservaDialogFragment(instalacion.getIdInstalacion(), listener);
            dialog.show(((FragmentActivity) context).getSupportFragmentManager(), "ReservaDialog");
        });

    }

    @Override
    public int getItemCount() {
        return instalacionList.size();
    }

    static class InstalacionViewHolder extends RecyclerView.ViewHolder {
        final ImageView imagenImageView;
        final TextView nombreTextView;
        final TextView descripcionTextView;
        final TextView costoTextView;
        final Button reservarButton;

        InstalacionViewHolder(View itemView) {
            super(itemView);
            imagenImageView = itemView.findViewById(R.id.instalacion_imagen);
            nombreTextView = itemView.findViewById(R.id.instalacion_nombre);
            descripcionTextView = itemView.findViewById(R.id.instalacion_descripcion);
            costoTextView = itemView.findViewById(R.id.instalacion_costo);
            reservarButton = itemView.findViewById(R.id.instalacion_reservar_boton);
        }
    }
}
