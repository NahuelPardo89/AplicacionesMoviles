package com.example.ampamain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  datos del formulario de login
                EditText inputEmail = findViewById(R.id.inputEmail);
                EditText inputPassword = findViewById(R.id.inputPassword);
                String username = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                // Verifica las credenciales
                if (verifyCredentials(username, password)) {
                    // Si las credenciales son correctas, navega a MainActivity
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish();  // Opcional: finaliza LoginActivity para que el usuario no pueda volver a ella
                } else {
                    // Si las credenciales son incorrectas, muestra un mensaje de error
                    Toast.makeText(login.this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // Método placeholder para verificar las credenciales
    private boolean verifyCredentials(String username, String password) {
        // Agregar aqui toda la logica del login
        return username.equals("user") && password.equals("password");
    }
}