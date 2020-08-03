package com.example.alarm.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
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
        button1.setOnClickListener {
            display.setText(display.text.toString() + "1")
        }
        button2.setOnClickListener {
            display.setText(display.text.toString() + "2")
        }
        button3.setOnClickListener {
            display.setText(display.text.toString() + "3")
        }
        button4.setOnClickListener {
            display.setText(display.text.toString() + "4")
        }
        button5.setOnClickListener {
            display.setText(display.text.toString() + "5")
        }
        button6.setOnClickListener {
            display.setText(display.text.toString() + "6")
        }
        button7.setOnClickListener {
            display.setText(display.text.toString() + "7")
        }
        button8.setOnClickListener {
            display.setText(display.text.toString() + "8")
        }
        button9.setOnClickListener {
            display.setText(display.text.toString() + "9")
        }
        button0.setOnClickListener {
            display.setText(display.text.toString() + "0")
        }
        backClear.setOnClickListener {
            display?.text.toString().dropLast(1)
        }
        changeVisibility.setOnClickListener {
            if (buttonsView.visibility == View.VISIBLE){
                setTimmerView.visibility = View.VISIBLE
                buttonsView.visibility = View.GONE
            }else{
                buttonsView.visibility = View.VISIBLE
                setTimmerView.visibility = View.GONE
            }
        }
        
    }
}