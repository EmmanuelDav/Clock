package com.example.alarm.Fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alarm.R
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : Fragment() {
    companion object {
        var sStartTimer: Long = 60_000
        var sTimerLeft: Long = sStartTimer
        var sRunning: Boolean = false

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonPlay()
    }


    private fun buttonPlay() {
        startTimer.setOnClickListener {
            startTimer.visibility = View.GONE
            stop_resumeTimer.visibility = View.VISIBLE
            stop_resumeTimer.text = "Stop"
            reset_LapTimer.visibility = View.VISIBLE
            reset_LapTimer.text = "Lap"
            startTimer()
        }

    }

    private fun startTimer() {
        if (!sRunning) {
            timerProgress.max = sStartTimer.toInt()
            val timer = object : CountDownTimer(sTimerLeft,1_000) {
                override fun onFinish() {

                }

                override fun onTick(millisUntilFinished: Long) {

                }
            }
            sRunning = true
        }
    }


}