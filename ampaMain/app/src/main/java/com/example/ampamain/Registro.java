package com.example.ampamain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button registroButton = findViewById(R.id.btn_registrar_registro);
        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Muestra un Snackbar
                Snackbar.make(view, "Usuario registrado", Snackbar.LENGTH_LONG).show();

                // Navega hacia LoginActivity después de un breve retraso
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Registro.this, login.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1500);
            }
        });
    }
}