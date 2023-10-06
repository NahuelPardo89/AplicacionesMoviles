package com.example.ampamain.ui;

import android.os.Bundle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ampamain.R;

public class ChangePasswordFragment extends Fragment {


    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Views
        EditText password1EditText = view.findViewById(R.id.inputPassword1_change);
        EditText password2EditText = view.findViewById(R.id.inputPassword2_change);
        Button btnCambiar = view.findViewById(R.id.buttonCambiar_password);

        btnCambiar.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Contraseña actualizada", Toast.LENGTH_SHORT).show();


            NavHostFragment.findNavController(this).navigate(R.id.nav_home);
        });
    }
}