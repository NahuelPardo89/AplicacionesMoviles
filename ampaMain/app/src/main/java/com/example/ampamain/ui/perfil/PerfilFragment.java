package com.example.ampamain.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.example.ampamain.R;
import com.bumptech.glide.Glide;
import com.example.ampamain.UserProfile;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private TextView dniText, nameText, emailText, isActiveText;
    private ImageView profileImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Inicialización de los elementos de la vista
        dniText = root.findViewById(R.id.dni);
        nameText = root.findViewById(R.id.nombre);
        emailText = root.findViewById(R.id.email);
        isActiveText = root.findViewById(R.id.is_active);
        profileImage = root.findViewById(R.id.profile_image);

        // Observar los cambios en el perfil del usuario
        perfilViewModel.getUserProfile().observe(getViewLifecycleOwner(), this::updateUI);

        // botón Editar para navegar al fragmento de edición
        root.findViewById(R.id.edit_button).setOnClickListener(v -> {
            // Navegar hacia el fragmento de edición (asumiendo que se llamará EditProfileFragment)
            NavHostFragment.findNavController(this).navigate(R.id.editProfileFragment);
        });

        return root;
    }

    private void updateUI(UserProfile userProfile) {
        dniText.setText(userProfile.getDni().toString());
        nameText.setText(userProfile.getNombre());
        emailText.setText(userProfile.getEmail());
        isActiveText.setText(userProfile.isIsActive() ? "Activo" : "Inactivo");
        Glide.with(this)
                .load(userProfile.getFotoUrl())
                .into(profileImage);
    }
}
