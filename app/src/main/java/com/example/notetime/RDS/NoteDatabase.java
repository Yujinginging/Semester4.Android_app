package com.example.notetime.RDS;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.notetime.Model.Note;

@androidx.room.Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public static NoteDatabase instance;
    public abstract NoteDAO getnoteDAO();


    public static synchronized NoteDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class
            ,"note_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
