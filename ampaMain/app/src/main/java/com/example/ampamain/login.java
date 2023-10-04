package com.example.ampamain;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private TextInputLayout emailInputLayout, passwordInputLayout;
    private Button loginButton, registerButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializa Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.inputEmail_login);
        passwordEditText = findViewById(R.id.inputPassword_Login);
        emailInputLayout = findViewById(R.id.inputEmail_login_layout);
        passwordInputLayout = findViewById(R.id.inputPassword_Login_layout);
        loginButton = findViewById(R.id.btnLogin_Login);
        registerButton = findViewById(R.id.btnRegistrarse_login);

        // TextWatcher para validación en tiempo real
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                validateEmail();
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                validatePassword();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    loginUser();
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, Registro.class);
                startActivity(intent);
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

    private boolean validatePassword() {
        String password = passwordEditText.getText().toString().trim();
        if (password.isEmpty() || !isValidPassword(password)) {
            passwordInputLayout.setError("La contraseña debe tener al menos 8 caracteres, un número y una letra mayúscula.");
            return false;
        } else {
            passwordInputLayout.setError(null);
            return true;
        }
    }

    private boolean validateForm() {
        return validateEmail() & validatePassword();
    }

    private boolean isValidPassword(String password) {
        Pattern pattern;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(login.this, "Autenticación exitosa.", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(login.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    } else {
                        // Si la autenticación falla, muestra un mensaje al usuario.
                        Toast.makeText(login.this, "Autenticación fallida.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Verifica si el usuario está autenticado (no nulo) y actualiza la interfaz en consecuencia.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(this, "Usuario ya autenticado.", Toast.LENGTH_SHORT).show();
            // Aquí puedes dirigir al usuario a la pantalla principal de tu aplicación o cualquier otra actividad
        }
    }
}
