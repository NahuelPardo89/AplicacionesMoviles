package com.example.ampamain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button buttonToLogin = findViewById(R.id.btnLoginLogin);
        buttonToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent para iniciar LoginActivity
                Intent intent = new Intent(welcome.this, login.class);
                // Inicia LoginActivity
                startActivity(intent);
            }
        });
    }
}