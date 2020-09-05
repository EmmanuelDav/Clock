package com.example.alarm.Fragments

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.alarm.Adapters.LapAdapter
import com.example.alarm.Model.Lap
import com.example.alarm.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StopWatchFragment : Fragment() {
    companion object {
        var isRunning: Boolean = false
        var offPauseValue: Long = 0
        var saveValue: Long = 0
    }

    private var mLapAdapter: LapAdapter? = null
    private var mLapRecyclerView: RecyclerView? = null

    var mchronometer: Chronometer? = null
    var startAlarm: FloatingActionButton? = null
    var pause: ImageView? = null
    var stopTimer: ImageView? = null
    var setLap: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        mchronometer = view.findViewById<Chronometer>(R.id.chronometer)
        startAlarm = view.findViewById<FloatingActionButton>(R.id.startAlarm)
        pause = view.findViewById<ImageView>(R.id.pause)
        setLap = view.findViewById(R.id.mSetLap)
        stopTimer = view.findViewById<ImageView>(R.id.stopTimer)
        startStopWatch()
        initializeLapList(view)
        pause?.setOnClickListener {
            pauseStopWatch()
        }
        stopTimer?.setOnClickListener {
            resetStopWatch()
        }
        setLap?.setOnClickListener {
            createLap()
            Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show()
        }
        return view
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
        mLapAdapter?.clearLap()
    }

    override fun onStop() {
        super.onStop()
        saveValue = (SystemClock.elapsedRealtime() - mchronometer?.base!!)
    }

    override fun onStart() {
        super.onStart()
        if (isRunning) {
            mchronometer?.base = SystemClock.elapsedRealtime() - saveValue
            mchronometer?.start()
            isRunning = true
        }
    }

    private fun initializeLapList(view: View) {
        mLapRecyclerView = view.findViewById(R.id.ChromRecycler) as RecyclerView
        mLapRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        mLapAdapter = LapAdapter()
        mLapRecyclerView!!.adapter = mLapAdapter
    }

    private fun createLap() {
        mLapAdapter?.addLap(Lap(SystemClock.elapsedRealtime() - mchronometer?.base!!))
        if (mLapAdapter!!.itemCount > 0) {
            mLapRecyclerView!!.smoothScrollToPosition(mLapAdapter!!.itemCount - 1)
        }
    }
}