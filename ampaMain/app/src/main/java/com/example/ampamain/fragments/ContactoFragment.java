package com.example.ampamain.fragments;
import android.content.Intent;


import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ampamain.R;
import com.google.android.material.snackbar.Snackbar;


public class ContactoFragment extends Fragment {

        private EditText nombreEditText, emailEditText, mensajeEditText;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_contacto, container, false);

            nombreEditText = root.findViewById(R.id.inputNombreContacto);
            emailEditText = root.findViewById(R.id.inputEmailContacto);
            mensajeEditText = root.findViewById(R.id.contactUsMessage);

            Button enviarButton = root.findViewById(R.id.buttonEnviarContacto);
            enviarButton.setOnClickListener(v -> enviarEmail());

            return root;
        }

        private void enviarEmail() {
            String nombre = nombreEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String mensaje = mensajeEditText.getText().toString();

            String subject = "Mensaje de: " + nombre + ", Email: " + email;
            String to = "nahuel.pardo74@gmail.com";

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar correo..."));



                // Redireccionar a Home después de 3 segundos
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Mostrar el Snackbar

                        NavHostFragment.findNavController(ContactoFragment.this).navigate(R.id.nav_home);
                        Snackbar.make(getView(), "Mensaje enviado con éxito", Snackbar.LENGTH_LONG).show();
                    }
                }, 3000);

            } catch (android.content.ActivityNotFoundException ex) {
                // No hay cliente de correo instalado
                Snackbar.make(getView(), "No se encontró una aplicación de correo", Snackbar.LENGTH_LONG).show();
            }
        }
    }