package com.example.ampamain.fragments;

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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class ChangePasswordFragment extends Fragment {

    private EditText currentPasswordEditText;
    private EditText password1EditText;
    private EditText password2EditText;
    private TextInputLayout currentPasswordInputLayout;
    private TextInputLayout password1InputLayout;
    private TextInputLayout password2InputLayout;

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
        currentPasswordEditText = view.findViewById(R.id.inputCurrentPassword_change);
        password1EditText = view.findViewById(R.id.inputPassword1_change);
        password2EditText = view.findViewById(R.id.inputPassword2_change);
        currentPasswordInputLayout = view.findViewById(R.id.inputCurrentPassword_change_layout);
        password1InputLayout = view.findViewById(R.id.inputPassword_change_layout);
        password2InputLayout = view.findViewById(R.id.inputPassword2_change_layout);
        Button btnCambiar = view.findViewById(R.id.buttonCambiar_password);

        btnCambiar.setOnClickListener(v -> {
            // Validaciones
            if (!validatePassword(currentPasswordEditText, currentPasswordInputLayout) ||
                    !validatePassword(password1EditText, password1InputLayout) ||
                    !validatePasswordEqual()) {
                return;
            }

            String currentPassword = currentPasswordEditText.getText().toString();
            String newPassword1 = password1EditText.getText().toString();

            // Lógica de Firebase
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (user != null) {
                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), currentPassword);
                user.reauthenticate(credential).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        user.updatePassword(newPassword1).addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Toast.makeText(getContext(), "Contraseña actualizada", Toast.LENGTH_SHORT).show();
                                NavHostFragment.findNavController(this).navigate(R.id.nav_home);
                            } else {
                                Toast.makeText(getContext(), "Error al actualizar la contraseña", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Contraseña actual incorrecta", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private boolean validatePassword(EditText passwordEditText, TextInputLayout inputLayout) {
        String password = passwordEditText.getText().toString().trim();
        if (password.isEmpty() || !isValidPassword(password)) {
            inputLayout.setError("La contraseña debe tener al menos 8 caracteres, un número y una letra mayúscula.");
            return false;
        } else {
            inputLayout.setError(null);
            return true;
        }
    }

    private boolean validatePasswordEqual() {
        String password1 = password1EditText.getText().toString().trim();
        String password2 = password2EditText.getText().toString().trim();
        if (!password1.equals(password2)) {
            password2InputLayout.setError("Las contraseñas no coinciden.");
            return false;
        } else {
            password2InputLayout.setError(null);
            return true;
        }
    }

    private boolean isValidPassword(String password) {
        Pattern pattern;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }
}
