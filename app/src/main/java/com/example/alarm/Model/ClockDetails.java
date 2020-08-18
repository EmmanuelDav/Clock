package com.example.alarm.Model;

public class ClockDetails {

    public String time;
    public String country;
    public String date;

    public ClockDetails(String nTime, String nCountry, String nDate) {
        time = nTime;
        country = nCountry;
        date = nDate;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String nDate) {
        date = nDate;
    }
}
