package com.example.alarm.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarm.Model.AlarmDetails
import com.example.alarm.R
import kotlinx.android.synthetic.main.alarm_details_view.view.*

class AlarmDetailsAdapter( var alarmDetails: List<AlarmDetails>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.alarm_details_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = alarmDetails.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var sAlarmDetails: AlarmDetails = alarmDetails[position]
        when
            (holder) {
            is ViewHolder -> {
                holder.itemView.time?.text=sAlarmDetails.time
                holder.itemView.amOrPm?.text=sAlarmDetails.amOrPm
                holder.itemView.Date?.text=sAlarmDetails.dateForAlarm
            }
        }
    }
}
