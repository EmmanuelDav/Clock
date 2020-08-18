package com.example.alarm.Utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class WorldClockConverters {

    public static String clockBasedOnCountries(String country) {
        DateTime nDateTime1 = new DateTime(System.currentTimeMillis(), DateTimeZone.forID("Africa/Lagos"));
        DateTimeFormatter nWithZone = DateTimeFormat
                .forPattern("EEE/MM/yy hh:mm a")
                .withZone(DateTimeZone.forID(country));
        return nWithZone.print(nDateTime1);
    }

    public static String dateFromPattern(String numbers) {
        String firstFour = "";
        if (clockBasedOnCountries(numbers).length() >= 10) {
            return firstFour = clockBasedOnCountries(numbers).substring(0, 10);
        } else {
            return numbers;
        }
    }

    public static String timeFromPattern(String date) {
        String firstFour = "";
        if (clockBasedOnCountries(date).length() >= 10) {
            return firstFour = clockBasedOnCountries(date).substring(clockBasedOnCountries(date).length() - 8);
        } else {
            return date;
        }
    }
}
