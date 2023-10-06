package com.example.ampamain.ui.perfil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ampamain.R;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private TextView nameTextView, emailTextView, dniTextView;
    private ImageView profileImageView;

    private Button btnEditar;
    private FirebaseAuth mAuth;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Views
        nameTextView = getView().findViewById(R.id.nombreTextView_perfil);
        emailTextView = getView().findViewById(R.id.emailTextView_perfil);
        profileImageView = getView().findViewById(R.id.profileImageView);
        btnEditar=getView().findViewById(R.id.edit_button_perfil);


        updateUI();
        btnEditar.setOnClickListener(v -> {
            // Navegar hacia el fragmento de edición (asumiendo que se llamará EditProfileFragment)
            NavHostFragment.findNavController(this).navigate(R.id.editProfileFragment);
        });

    }

    private void updateUI() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            nameTextView.setText( user.getDisplayName());
            emailTextView.setText( user.getEmail());

            // Fetch and set user's profile picture if available
            Uri photoUri = user.getPhotoUrl();
            if (photoUri != null) {
                new Thread(() -> {
                    try {
                        Bitmap bitmap = getBitmapFromURL(photoUri.toString());
                        getActivity().runOnUiThread(() -> profileImageView.setImageBitmap(bitmap));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    public Bitmap getBitmapFromURL(String src) throws IOException {
        URL url = new URL(src);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        return BitmapFactory.decodeStream(input);
    }
}
