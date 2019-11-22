package com.example.notetime.RDS;

import com.example.notetime.Model.Time;
import com.google.gson.annotations.SerializedName;

public class TimeResponse {

    @SerializedName("timezone")
    String timezone;

    @SerializedName("utc_datetime")
    String utc_datetime;

    public Time getTime(){
        return new Time(timezone,utc_datetime);
    }
}
