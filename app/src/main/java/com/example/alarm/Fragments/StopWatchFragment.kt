package com.example.alarm.Fragments

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.fragment.app.Fragment
import com.example.alarm.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_stop_watch.*

class StopWatchFragment : Fragment() {
    companion object {
        var isRunning: Boolean = false
        var offPauseValue: Long = 0
    }

    var mchronometer: Chronometer? = null
    var startAlarm: FloatingActionButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_stop_watch, container, false)
        mchronometer = v.findViewById<Chronometer>(R.id.chronometer)
        startAlarm = v.findViewById<FloatingActionButton>(R.id.startAlarm)

        startAlarm?.setOnClickListener {
            startStopWatch()
        }
//        pause.setOnClickListener {
//            pauseStopWatch()
//        }
//
//        stopTimer.setOnClickListener {
//            resetStopWatch()
//        }
        return v;
    }


    private fun startStopWatch() {
        if (!isRunning) {
            mchronometer?.base = SystemClock.elapsedRealtime() - offPauseValue
            mchronometer?.start()
            isRunning = true
        }
    }

    private fun pauseStopWatch() {
        if (isRunning) {
            mchronometer?.stop()
            offPauseValue = (SystemClock.elapsedRealtime() - chronometer.base)
            isRunning = false
        }
    }

    private fun resetStopWatch() {
        mchronometer?.stop()
        mchronometer?.base = SystemClock.elapsedRealtime()
        offPauseValue = 0
        isRunning = false
    }
}