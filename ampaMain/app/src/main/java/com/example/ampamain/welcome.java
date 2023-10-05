package com.example.ampamain;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class welcome extends AppCompatActivity {

    // Duración en milisegundos que mostrarás la pantalla de bienvenida
    private static int SPLASH_SCREEN_TIME_OUT = 2000;  // 2 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    // Si el usuario ya está autenticado, redirigimos a MainActivity
                    Intent intent = new Intent(welcome.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // Si el usuario no está autenticado, redirigimos a LoginActivity
                    Intent intent = new Intent(welcome.this, login.class);
                    startActivity(intent);
                }
                // Finaliza la actividad Welcome para que el usuario no pueda regresar a ella con el botón atrás
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}