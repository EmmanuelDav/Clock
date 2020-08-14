package com.example.alarm.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.example.alarm.R;

import java.util.Locale;

public class TimeDialog extends DialogFragment {

   private onPositiveButtonClickListener onPositiveButtonClickListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle mBundle = getArguments();
        int h = (int) mBundle.getLong("timer", 600_00) / 1_000 / 60 / 60 % 24;
        int m = (int) mBundle.getLong("timer", 600_00) / 1_000 / 60 % 60;
        int s = (int) mBundle.getLong("timer", 600_00) / 1_000 % 60;

        final View numberPicker = View.inflate(getContext(), R.layout.time_picker_fragment, null);
        final NumberPicker hours = numberPicker.findViewById(R.id.number_picker_hours);
        final NumberPicker minutes = numberPicker.findViewById(R.id.number_picker_minutes);
        final NumberPicker seconds = numberPicker.findViewById(R.id.number_picker_seconds);

        hours.setMaxValue(23);
        hours.setMinValue(0);
        hours.setValue(h);

        minutes.setMaxValue(59);
        minutes.setMinValue(0);
        minutes.setValue(m);

        seconds.setMaxValue(23);
        seconds.setMinValue(0);
        seconds.setValue(s);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("set time for timer")
                .setView(numberPicker)
                .setPositiveButton("set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long fullTime =
                                (hours.getValue() * 60 * 60 * 1_000)+
                                        (minutes.getValue() * 60 * 1_000) +
                                        (seconds.getValue() * 1_000);
                        onPositiveButtonClickListener.onPositiveClickListener(fullTime);
                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

    public  void setOnPositiveButtonClickListener(onPositiveButtonClickListener mp){
        onPositiveButtonClickListener = mp;
    }

    public interface onPositiveButtonClickListener {
        void onPositiveClickListener(long fullTime);
    }

}