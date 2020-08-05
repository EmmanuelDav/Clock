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

        sAlarmDetails.add(AlarmDetails("7:30", "Tuesday", true, "Am"))
        sAlarmDetails.add(AlarmDetails("7:30", "Tuesday", true, "Am"))
    }
}

