package com.example.ampamain.ui;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ampamain.R;
import com.example.ampamain.UserProfile;
import com.example.ampamain.ui.perfil.PerfilViewModel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class CredencialFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private TextView dniTextView, nameTextView, apellidoTextView, isActiveTextView;
    private ImageView profileImage, qrCodeImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_credencial, container, false);

        // Inicialización de los elementos de la vista
        dniTextView = root.findViewById(R.id.dni_text);
        nameTextView = root.findViewById(R.id.nombre_text);
        apellidoTextView = root.findViewById(R.id.apellido_text);
        isActiveTextView = root.findViewById(R.id.is_active_text);
        profileImage = root.findViewById(R.id.profile_image);
        qrCodeImage = root.findViewById(R.id.qr_code_image);

        // Observar los cambios en el perfil del usuario
        perfilViewModel.getUserProfile().observe(getViewLifecycleOwner(), this::updateUI);

        return root;
    }

    private void updateUI(UserProfile userProfile) {
        dniTextView.setText("DNI: " + userProfile.getDni().toString());
        nameTextView.setText("Nombre: " + userProfile.getNombre());
        apellidoTextView.setText("Apellido: " + userProfile.getApellido());
        isActiveTextView.setText("Estado: " + (userProfile.isIsActive() ? "Activo" : "Inactivo"));
        // ... código para cargar la imagen del perfil si es necesario

        // Generar y mostrar el código QR basado en el DNI
        String dni = userProfile.getDni().toString();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(dni, BarcodeFormat.QR_CODE, 200, 200);
            Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 200; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? android.graphics.Color.BLACK : android.graphics.Color.WHITE);
                }
            }
            qrCodeImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}