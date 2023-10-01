package com.example.ampamain.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.example.ampamain.R;
import com.bumptech.glide.Glide;
import com.example.ampamain.UserProfile;

public class EditProfileFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private EditText dniEditText, nameEditText, apellidoEditText, emailEditText;
    private ImageView profileImage;
    private Button saveButton, changePhotoButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        dniEditText = root.findViewById(R.id.dni);
        nameEditText = root.findViewById(R.id.nombre);
        apellidoEditText = root.findViewById(R.id.apellido);
        emailEditText = root.findViewById(R.id.email);
        profileImage = root.findViewById(R.id.profile_image);
        saveButton = root.findViewById(R.id.save_button);
        changePhotoButton = root.findViewById(R.id.change_photo_button);

        perfilViewModel.getUserProfile().observe(getViewLifecycleOwner(), this::updateUI);

        saveButton.setOnClickListener(this::onSaveClick);
        changePhotoButton.setOnClickListener(v -> {
            // Lógica para permitir al usuario cambiar su foto
        });

        return root;
    }

    private void updateUI(UserProfile userProfile) {
        dniEditText.setText(userProfile.getDni().toString());
        nameEditText.setText(userProfile.getNombre());
        apellidoEditText.setText(userProfile.getApellido());
        emailEditText.setText(userProfile.getEmail());
        Glide.with(this)
                .load(userProfile.getFotoUrl())
                .into(profileImage);
    }

    public void onSaveClick(View view) {
        UserProfile updatedProfile = new UserProfile(
                Integer.parseInt(dniEditText.getText().toString()),
                nameEditText.getText().toString(),
                apellidoEditText.getText().toString(),
                emailEditText.getText().toString(),
                perfilViewModel.getUserProfile().getValue().isIsActive(),
                ""  // URL de la foto, puedes obtenerlo de la misma manera si la URL cambia
        );
        perfilViewModel.updateUserProfile(updatedProfile);
    }
}
