package com.example.alarm.Model;

public class ClockDetails {

    public String time;
    public String country;
    public String timeConvention;

    public ClockDetails(String nTime, String nCountry, String nTimeConvention)
    {
        time = nTime;
        country = nCountry;
        timeConvention = nTimeConvention;
    }

    public ClockDetails(){}

    public String getTime() {
        return time;
    }

    public void setTime(String nTime) {
        time = nTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String nCountry) {
        country = nCountry;
    }

    public String getTimeConvention() {
        return timeConvention;
    }

    public void setTimeConvention(String nTimeConvention) {
        timeConvention = nTimeConvention;
    }
}
