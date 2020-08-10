package com.example.alarm.Fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import com.example.alarm.R
import kotlinx.android.synthetic.main.fragment_timer.*
import java.util.*

class TimerFragment : Fragment() {
    companion object {
        var sStartTimer: Long = 60_000 // one min
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
            timerProgress.progress = sTimerLeft.toInt()
            val timer = object : CountDownTimer(sTimerLeft, 1_000) {
                override fun onFinish() {
                    textTimer.text = "Finished"
                }

                override fun onTick(millisUntilFinished: Long) {
                    var sObjectAnimator: ObjectAnimator = ObjectAnimator
                        .ofInt(
                            timerProgress,
                            "ProgressBar",
                            timerProgress.progress,
                            millisUntilFinished.toInt()
                        )
                    sObjectAnimator.duration = 300
                    sObjectAnimator.interpolator = LinearInterpolator()
                    sObjectAnimator.start()
                    timerProgress.progress = millisUntilFinished.toInt()
                    sTimerLeft = millisUntilFinished
                    updateTimerText()
                }
            }
            timer.start()
            sRunning = true
        }

    }

    private fun updateTimerText() {
        val seconds = sTimerLeft.toInt() / 1000 % 60 //seconds
        val minutes = sTimerLeft.toInt() / 1000 / 60 % 60 // min
        val hours = sTimerLeft.toInt() / 1000 / 60 / 60 % 24 // hours
        val timeLeftFormatted = String.format(
            Locale.getDefault(),
            "%02d:%02d:%02d",
            hours,
            minutes,
            seconds
        )
        textTimer.text = timeLeftFormatted
    }

}