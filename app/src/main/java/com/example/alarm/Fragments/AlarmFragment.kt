package com.example.alarm.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alarm.Adapters.AlarmDetailsAdapter
import com.example.alarm.Model.AlarmDetails
import com.example.alarm.R
import kotlinx.android.synthetic.main.fragment_alarm.*

class AlarmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sAlarmDetails = ArrayList<AlarmDetails>()
        AlarmRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        AlarmRecyclerview.adapter = AlarmDetailsAdapter(sAlarmDetails)
        sAlarmDetails.add(AlarmDetails("07:30", "Alarm 1h, 30min", true, "pm"))
        sAlarmDetails.add(AlarmDetails("01:50", "Alarm 7h, 29min", true, "Am"))
        sAlarmDetails.add(AlarmDetails("07:40", "Alarm 3h, 19min", true, "Am"))
        sAlarmDetails.add(AlarmDetails("03:30", "Alarm 6h, 29min", true, "Am"))
        sAlarmDetails.add(AlarmDetails("06:20", "Alarm 0h, 27min", true, "pm"))
        sAlarmDetails.add(AlarmDetails("06:20", "Alarm 0h, 27min", true, "pm"))
    }
}

