package com.example.alarm.Fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alarm.R
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addButtons()


    }

    private fun addButtons() {

//        startTimer.setOnClickListener {
//            val timer = object : CountDownTimer(30000, 1000) {
//                override fun onFinish() {
//                    timer.text = "Finished"
//                    var mediaPlayer = MediaPlayer.create(context, R.raw.song);
//                    mediaPlayer.start();
//                }
//                override fun onTick(millisUntilFinished: Long) {
//                    timer.text = display.text.toString()
//                    timer.text = (millisUntilFinished /1000).toString()
//                    seekBar.progress = (millisUntilFinished / 1000).toInt()
//                }
//            }
//            timer.start()
//        }
    }
}