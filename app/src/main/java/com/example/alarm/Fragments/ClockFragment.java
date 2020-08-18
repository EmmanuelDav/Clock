package com.example.alarm.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarm.Adapters.ClockAdapter;
import com.example.alarm.Model.ClockDetails;
import com.example.alarm.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.alarm.Utils.WorldClockConverters.clockBasedOnCountries;
import static com.example.alarm.Utils.WorldClockConverters.dateFromPattern;
import static com.example.alarm.Utils.WorldClockConverters.timeFromPattern;

public class ClockFragment extends Fragment {
    RecyclerView mRecyclerView;
    ClockAdapter mClockAdapter;
    ArrayList<ClockDetails> mClockArray;
    private static final String TAG = "ClockFragment";
    public static String india, europe, los_angeles, newYork;
    public static  String mIndia,mEurope,mLos_angeles,mNewYork;
    TextView myDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clock, container, false);
        myDate = view.findViewById(R.id.testClock);
        myDate.setText(LocalTime());
        mRecyclerView = view.findViewById(R.id.clockRecyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mClockArray = new ArrayList<>();
        mClockArray.add(new ClockDetails("", "Los Angeles", ""));
        mClockArray.add(new ClockDetails("", "London",""));
        mClockArray.add(new ClockDetails("", "New York", ""));
        mClockArray.add(new ClockDetails("", "India", ""));
        mClockAdapter = new ClockAdapter(mClockArray, getContext());
        mRecyclerView.setAdapter(mClockAdapter);
        setTimerEveryMin();
        changeDateEvery24Hours();
        return view;
    }

    private void setTimerEveryMin() {
        final Handler nHandler = new Handler();
        Timer nTimer = new Timer();
        TimerTask nTimerTask = new TimerTask() {
            @Override
            public void run() {
                nHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        initForTime();
                        mClockAdapter.timer();
                    }
                });
            }
        }; nTimer.schedule(nTimerTask, 10,60*1000);
    }

    void initForTime() {
        los_angeles = timeOfCountry("America/Los_Angeles");
        europe = timeOfCountry("Europe/Minsk");
        newYork = timeOfCountry("America/New_York");
        india = timeOfCountry("Indian/Comoro");
    }

    private String timeOfCountry(String CountryGMT) {
        return timeFromPattern(CountryGMT);
    }
    private  String DateOfCountries(String Country){
        return  dateFromPattern(Country);
    }

    private void changeDateEvery24Hours() {
        final Handler nHandler = new Handler();
        Timer nTimer = new Timer();
        TimerTask nTimerTask = new TimerTask() {
            @Override
            public void run() {
                nHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        initForDate();
                        mClockAdapter.date();

                    }
                });
            }
        }; nTimer.schedule(nTimerTask, 10,24 * 60 * 60 * 1_000);

    }

    void initForDate() {
        mLos_angeles = DateOfCountries("America/Los_Angeles");
        mEurope = DateOfCountries("Europe/Minsk");
        mNewYork = DateOfCountries("America/New_York");
        mIndia = DateOfCountries("Indian/Comoro");
    }


    String LocalTime(){
        String date = new SimpleDateFormat("dd-MMMM-yy", Locale.getDefault()).format(new Date());
        return  date;
    }
}
