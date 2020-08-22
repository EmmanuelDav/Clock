package com.example.alarm.Fragments

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.ImageView
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
    var pause: ImageView? = null
    var stopTimer: ImageView? = null
    var chronBundle:Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        mchronometer = view.findViewById<Chronometer>(R.id.chronometer)
        startAlarm = view.findViewById<FloatingActionButton>(R.id.startAlarm)
        pause = view.findViewById<ImageView>(R.id.pause)
        stopTimer = view.findViewById<ImageView>(R.id.stopTimer)

        startStopWatch()

        pause?.setOnClickListener {
            pauseStopWatch()
        }

        stopTimer?.setOnClickListener {
            resetStopWatch()
        }
        return  view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null){
            offPauseValue = savedInstanceState.getLong("Chronometer_Value")
            mchronometer!!.base = offPauseValue
            mchronometer!!.start()
        }
    }


    private fun startStopWatch() {
        startAlarm?.setOnClickListener {
            if (!isRunning) {
                mchronometer?.base = SystemClock.elapsedRealtime() - offPauseValue
                mchronometer?.start()
                isRunning = true
            }
        }

    }

    private fun pauseStopWatch() {
        if (isRunning) {
            mchronometer?.stop()
            offPauseValue = (SystemClock.elapsedRealtime() - mchronometer?.base!!)
            isRunning = false
        }
    }

    private fun resetStopWatch() {
        mchronometer?.stop()
        mchronometer?.base = SystemClock.elapsedRealtime()
        isRunning = false
        offPauseValue = 0
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("Chronometer_Value", SystemClock.elapsedRealtime() - mchronometer?.base!!)
    }


    private fun returnBundle(): Bundle {
        val bundle = Bundle()
        bundle.getLong("forActivityRestart", offPauseValue)
        return bundle
    }

    override fun onStop() {
        super.onStop()
        chronBundle = returnBundle()
    }

    override fun onStart() {
        super.onStart()
        if (isRunning && chronBundle != null){
            offPauseValue = chronBundle!!.getLong("forActivityRestart")
            mchronometer!!.base = SystemClock.elapsedRealtime() - mchronometer?.base!!
            mchronometer!!.start()
        }
    }
}