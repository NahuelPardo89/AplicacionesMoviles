package com.example.ampamain.fragments;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.ampamain.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class CredencialFragment extends Fragment {

    private TextView userUidTextView;
    private TextView userNameTextView;
    private ImageView userImageImageView;
    private ImageView userQrCodeImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credencial, container, false);

        userUidTextView = view.findViewById(R.id.user_uid);
        userNameTextView = view.findViewById(R.id.user_name);
        userImageImageView = view.findViewById(R.id.user_image);
        userQrCodeImageView = view.findViewById(R.id.user_qr_code);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            // Carga la imagen del usuario usando Glide
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl())
                        .into(userImageImageView);
            } else {
                // Establece una imagen por defecto si el usuario no tiene una imagen de perfil
                userImageImageView.setImageResource(R.drawable.avatar);
            }
            userUidTextView.setText("UID: " + user.getUid());
            userNameTextView.setText("Nombre: " + user.getDisplayName());

            Bitmap qrCodeBitmap = generateQRCode(user.getUid());
            userQrCodeImageView.setImageBitmap(qrCodeBitmap);
        }

        return view;
    }

    private Bitmap generateQRCode(String uid) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(uid, BarcodeFormat.QR_CODE, 200, 200);
            Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 200; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? android.graphics.Color.BLACK : android.graphics.Color.WHITE);
                }
            }
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}