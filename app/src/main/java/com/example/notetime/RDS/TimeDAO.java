package com.example.notetime.RDS;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notetime.Model.Time;

import java.util.List;

@Dao
public interface TimeDAO {
    @Insert
    void insert(Time time);

    @Update
    void update(Time time);

    @Query("Select * from time_table ORDER BY city")
    LiveData<List<Time>> getAllTime();

    @Query("DELETE FROM time_table")
    void deleteAllTime();
}
