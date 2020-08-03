package com.example.alarm.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alarm.R;

import java.util.HashMap;
import java.util.Locale;


public class StopWatchFragment extends Fragment implements View.OnClickListener {
    private int second = 0;
    private boolean running;
    private boolean wasRunning;
    Button start, stop, reset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stop_watch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        start = view.findViewById(R.id.start_button);
        stop = view.findViewById(R.id.stop_button);
        reset = view.findViewById(R.id.reset_button);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        reset.setOnClickListener(this);
        if (savedInstanceState != null) {

            /**
             * get the previous sate of the stopwatch
             * if the activity is destroyed and recreated
             */
            second = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    /**
     * Save the state of the stopwatch
     * if the activity is destroyed
     */

    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState) {
        savedInstanceState
                .putInt("seconds", second);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }

    /***
     * if the activity is paused stop the stopwatch
     */
    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    // If the activity is resumed,
    // start the stopwatch
    // again if it was running previously.
    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }


    private void runTimer() {
        final TextView displayTime = getActivity().findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours  = second / 3600;
                int mins = (second % 3600) / 60;
                int secs = second % 60;

                String time =  String.format(Locale.getDefault(),"%d:%02d:%02d",hours,mins,secs);
                displayTime.setText(time);

                if(running){
                    second ++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                running = true;
                break;
            case R.id.stop_button:
                running = false;
                break;
            case R.id.reset_button:
                running = false;
                second = 0;
                break;
        }
    }
}