package com.example.alarm.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarm.Model.AlarmDetails
import com.example.alarm.R
import kotlinx.android.synthetic.main.alarm_details_view.view.*

class AlarmDetailsAdapter(private var alarmDetails: List<AlarmDetails>) :
    RecyclerView.Adapter<AlarmDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.alarm_details_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = alarmDetails.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var sAlarmDetails: AlarmDetails = alarmDetails[position]
        holder.bind(sAlarmDetails)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(alarmDetails: AlarmDetails) {
            itemView.time.text = alarmDetails.time
            itemView.amOrPm?.text = alarmDetails.amOrPm
            itemView.Date?.text = alarmDetails.dateForAlarm
        }
    }
}
