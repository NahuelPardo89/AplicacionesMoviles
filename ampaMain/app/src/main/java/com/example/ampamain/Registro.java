package com.example.ampamain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.net.Uri;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    private EditText emailEditText, password1EditText, password2EditText,nameEditText;
    private TextInputLayout emailInputLayout, password1InputLayout, password2InputLayout;
    private Button registerButton;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Initialize components
        initializeComponents();

        // Set listeners
        setEmailTextWatcher();
        setPassword1TextWatcher();
        setPassword2TextWatcher();
        setRegisterButtonClickListener();
    }

    private void initializeComponents() {
        emailEditText = findViewById(R.id.inputEmail_Registro);
        nameEditText = findViewById(R.id.inputNombre_Registro);
        password1EditText = findViewById(R.id.inputPassword1_registro);
        password2EditText = findViewById(R.id.inputPassword2_registro);
        emailInputLayout = findViewById(R.id.inputEmail_registro_layout);
        password1InputLayout = findViewById(R.id.inputPassword_register_layout);
        password2InputLayout = findViewById(R.id.inputPassword2_register_layout);
        registerButton = findViewById(R.id.btn_registrar_registro);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }

    private void setEmailTextWatcher() {
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                validateEmail();
            }
        });
    }

    private void setPassword1TextWatcher() {
        password1EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                validatePassword(password1EditText, password1InputLayout);
            }
        });
    }

    private void setPassword2TextWatcher() {
        password2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                validatePassword(password2EditText, password2InputLayout);
                validatePasswordEqual();
            }
        });
    }

    private void setRegisterButtonClickListener() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    registerUser();
                }
            }
        });
    }

    private boolean validateEmail() {
        String email = emailEditText.getText().toString().trim();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInputLayout.setError("Ingrese un email válido.");
            return false;
        } else {
            emailInputLayout.setError(null);
            return true;
        }
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

    private boolean validateForm() {
        return validateEmail() && validatePassword(password1EditText, password1InputLayout) && validatePassword(password2EditText, password2InputLayout) && validatePasswordEqual();
    }

    private boolean isValidPassword(String password) {
        Pattern pattern;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }

    private void registerUser() {
        progressDialog.setMessage("Registrando usuario...");
        progressDialog.show();
        String email = emailEditText.getText().toString().trim();
        String password = password1EditText.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null) {
                                updateProfile(user);
                            }
                        } else {
                            Toast.makeText(Registro.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateProfile(FirebaseUser user) {

        String defaultImageUrl = "https://firebasestorage.googleapis.com/v0/b/ampa-api.appspot.com/o/profileImages%2Favatar.png?alt=media&token=dab520d2-b45f-41d2-9b11-217972cb7b18";
        String displayName = nameEditText.getText().toString().trim();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(defaultImageUrl))
                .setDisplayName(displayName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Registro.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registro.this, login.class));
                        } else {
                            Toast.makeText(Registro.this, "Error al establecer imagen de perfil por defecto", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
