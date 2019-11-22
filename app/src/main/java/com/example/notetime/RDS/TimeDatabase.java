package com.example.notetime.RDS;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.notetime.Model.Time;

@Database(entities = Time.class,version = 1,exportSchema = false)
public abstract class TimeDatabase extends RoomDatabase {

    private static TimeDatabase instance;
    public abstract TimeDAO timeDAO();

    public static synchronized TimeDatabase getInstance(Context context)
    {
        if(instance == null){
        instance = Room.databaseBuilder(context.getApplicationContext(),
                TimeDatabase.class,"time_Database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
