package com.example.alarm.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alarm.Model.Lap
import com.example.alarm.R
import kotlinx.android.synthetic.main.row_lap.view.*
import java.util.*
import kotlin.collections.ArrayList

class LapAdapter() : RecyclerView.Adapter<LapAdapter.ViewHolder>() {

    private var mLapArrayList: MutableList<Lap> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LapAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_lap, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mLapArrayList.size


    fun addLap(lap: Lap) {
        mLapArrayList.add(lap)
        notifyItemInserted(mLapArrayList.size - 1)
    }

    fun clearLap() {
        mLapArrayList.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.timeId.text = i.plus(1).toString().plus(  ".")
        holder.time.text = getTimeInTextFormat(mLapArrayList[i].time!!)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val timeId: TextView = view.text_row_lap_id
        val time: TextView = view.text_row_lap_time
    }

    private fun getTimeInTextFormat(milliseconds: Long): String {
        val seconds = milliseconds / 1000 % 60 //seconds
        val minutes = milliseconds / 1000 / 60 % 60 // min
        val hours = milliseconds / 1000 / 60 / 60 % 24 // hours
        return String.format(
            Locale.getDefault(),
            "%02d:%02d:%02d",
            hours,
            minutes,
            seconds
        )
    }
}