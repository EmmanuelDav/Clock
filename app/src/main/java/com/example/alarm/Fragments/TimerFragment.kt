package com.example.alarm.Fragments

import android.animation.ObjectAnimator
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.example.alarm.ChannelIdApp
import com.example.alarm.MainActivity
import com.example.alarm.R
import com.example.alarm.Utils.BasicUtils
import kotlinx.android.synthetic.main.fragment_timer.*
import java.util.*

class TimerFragment : Fragment() {
    companion object {
        var sStartTimer: Long = 60_000 // one min
        var sTimerLeft: Long = sStartTimer
        var sRunning: Boolean = false
        lateinit var timer: CountDownTimer
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
        setTime()
        updateTimerText()
    }


    private fun buttonPlay() {
        startTimer.setOnClickListener {
            startTimer.visibility = View.GONE
            stop_resumeTimer.visibility = View.VISIBLE
            stop_resumeTimer.text = "Stop"
            reset_LapTimer.visibility = View.VISIBLE
            reset_LapTimer.text = "Reset"
            startTimer()
        }
        stop_resumeTimer.setOnClickListener {
            stop_resumeTimer.visibility = View.GONE
            reset_LapTimer.visibility = View.GONE
            startTimer.visibility = View.VISIBLE
            startTimer.text = "resume"
            pauseTimer()
        }
        reset_LapTimer.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        if (!sRunning) {
            timerProgress.max = sStartTimer.toInt()
            timerProgress.progress = sTimerLeft.toInt()
            timer = object : CountDownTimer(sTimerLeft, 1_000) {

                override fun onFinish() {
                    sTimerLeft = sStartTimer
                    sRunning = false
                    timerProgress.max = sStartTimer.toInt()
                    timerProgress.progress = sStartTimer.toInt()
                    updateTimerText()
                    ShowNotification()
                }

                override fun onTick(millisUntilFinished: Long) {
                    val sObjectAnimator: ObjectAnimator = ObjectAnimator
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

    private fun pauseTimer() {
        if (sRunning) {
            timer.cancel()
            sRunning = false
        }
    }

    private fun resetTimer() {
        timer.cancel()
        sStartTimer = 60_000
        sTimerLeft = sStartTimer
        sRunning = false
        updateTimerText()
        timerProgress.max = sStartTimer.toInt()
        timerProgress.progress = sStartTimer.toInt()
    }

    private fun setTime() {
        textTimer.setOnClickListener {
            if (!sRunning) {
                val bundle = Bundle()
                bundle.putLong("timer", sStartTimer)
                val timeDialog = TimeDialog()
                timeDialog.arguments = bundle
                timeDialog.show(fragmentManager!!, "TimerFragment")

                timeDialog.setOnPositiveButtonClickListener { fullTime ->
                    Log.d("Full Time", fullTime.toString())
                    sTimerLeft = fullTime
                    sStartTimer = sTimerLeft
                    updateTimerText()
                }
            }
        }
    }

    private fun ShowNotification() {
        BasicUtils.screenWake(context)
        // create the channel id

        val notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.putExtra("timer_cancel_notification", true)
        val sPendingIntent = PendingIntent.getActivity(
            context, 2,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val remoteView = RemoteViews(context!!.packageName,R.layout.notifiction_view)
        remoteView.setTextViewText(R.id.cancel_button_notification,"Stop")
        remoteView.setOnClickPendingIntent(R.id.cancel_button_notification,sPendingIntent)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context!!, ChannelIdApp.CHANNEL_ID)
        builder.setAutoCancel(true)
            .setCustomContentView(remoteView)
            .setTicker("Timer")
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setSmallIcon(R.drawable.ic_baseline_play_arrow_24)

        val notification: Notification = builder.build()
        notification.flags = notification.flags or Notification.FLAG_INSISTENT

        notificationManager.notify(2, notification)
    }
}