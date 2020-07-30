package com.example.alarm.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alarm.Adapters.AlarmDetailsAdapter
import com.example.alarm.Model.AlarmDetails
import com.example.alarm.R
import kotlinx.android.synthetic.main.fragment_alarm.*

class AlarmFragment : Fragment() {
    private val sAlarmDetails = ArrayList<AlarmDetails>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_alarm,container,false)
        var recyclerView = v.findViewById(R.id.AlarmRecyclerview) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = AlarmDetailsAdapter(sAlarmDetails)
        sAlarmDetails.add(AlarmDetails("7:30", "Tuesday", true, "Am"))
        sAlarmDetails.add(AlarmDetails("7:30", "Tuesday", true, "Am"))
        sAlarmDetails.add(AlarmDetails("7:30", "Tuesday", true, "Am"))
        sAlarmDetails.add(AlarmDetails("7:30", "Tuesday", true, "Am"))
        sAlarmDetails.add(AlarmDetails("7:30", "Tuesday", true, "Am"))
        sAlarmDetails.add(AlarmDetails("7:30", "Tuesday", true, "Am"))
        sAlarmDetails.add(AlarmDetails("7:30", "Tuesday", true, "Am"))
        return v
    }
}

