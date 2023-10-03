package com.example.ampamain;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton, registerButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializa Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.inputEmailLogin);
        passwordEditText = findViewById(R.id.inputPasswordLogin);
        loginButton = findViewById(R.id.btnLoginLogin);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });


    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

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
