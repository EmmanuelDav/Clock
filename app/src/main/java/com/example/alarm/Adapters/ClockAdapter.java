package com.example.alarm.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarm.Model.ClockDetails;
import com.example.alarm.R;

import java.util.ArrayList;

import static com.example.alarm.Fragments.ClockFragment.europe;
import static com.example.alarm.Fragments.ClockFragment.india;
import static com.example.alarm.Fragments.ClockFragment.los_angeles;
import static com.example.alarm.Fragments.ClockFragment.mEurope;
import static com.example.alarm.Fragments.ClockFragment.mIndia;
import static com.example.alarm.Fragments.ClockFragment.mLos_angeles;
import static com.example.alarm.Fragments.ClockFragment.mNewYork;
import static com.example.alarm.Fragments.ClockFragment.newYork;

public class ClockAdapter extends RecyclerView.Adapter<ClockAdapter.ViewHolder> {

    ArrayList<ClockDetails> mClockDetails;
    Context mContext;

    public ClockAdapter(ArrayList<ClockDetails> nClockDetails, Context nContext) {
        mClockDetails = nClockDetails;
        mContext = nContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(mContext).inflate(R.layout.clock_detail_view,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.Country.setText(mClockDetails.get(position).getCountry());
            holder.date.setText(mClockDetails.get(position).getDate());
            holder.timeConvention.setText(mClockDetails.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return mClockDetails.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder{
        TextView date,timeConvention,Country;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            timeConvention = itemView.findViewById(R.id.time);
            Country = itemView.findViewById(R.id.country);
        }
    }

    public void timer(){
        for (int i = 0; i<mClockDetails.size(); i++){
            mClockDetails.get(0).setTime(los_angeles);
            mClockDetails.get(1).setTime(europe);
            mClockDetails.get(2).setTime(newYork);
            mClockDetails.get(3).setTime(india);
            notifyDataSetChanged();
        }
    }

    public  void date(){
        for(int i =0; i<mClockDetails.size();i ++){
            mClockDetails.get(0).setDate(mLos_angeles);
            mClockDetails.get(1).setDate(mEurope);
            mClockDetails.get(2).setDate(mNewYork);
            mClockDetails.get(3).setDate(mIndia);
            notifyDataSetChanged();
        }
    }
}