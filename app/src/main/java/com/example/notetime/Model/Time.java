package com.example.notetime.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "time_table")
public class Time {
    @PrimaryKey(autoGenerate = true)
    private int id;


    private String time;
    private String city;

    public Time(String time, String city) {
        this.time = time;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
