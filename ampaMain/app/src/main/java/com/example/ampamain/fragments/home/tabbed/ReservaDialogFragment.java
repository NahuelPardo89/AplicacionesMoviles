package com.example.ampamain.fragments.home.tabbed;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import com.example.ampamain.R;

public class ReservaDialogFragment extends DialogFragment {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private int instalacionId;

    public interface ReservaListener {
        void onReservaConfirmada(int instalacionId, String fecha, String hora);
    }

    private ReservaListener listener;

    public ReservaDialogFragment(int instalacionId, ReservaListener listener) {
        this.instalacionId = instalacionId;
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_reserva, null);
        datePicker = view.findViewById(R.id.datePicker);
        timePicker = view.findViewById(R.id.timePicker);

        builder.setView(view)
                .setPositiveButton("Reservar", (dialog, id) -> {
                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth();
                    int year = datePicker.getYear();
                    String fecha = day + "/" + (month + 1) + "/" + year;

                    int hour = timePicker.getCurrentHour();
                    int minute = timePicker.getCurrentMinute();
                    String hora = hour + ":" + minute;

                    listener.onReservaConfirmada(instalacionId, fecha, hora);
                })
                .setNegativeButton("Cancelar", (dialog, id) -> ReservaDialogFragment.this.getDialog().cancel());

        return builder.create();
    }
}