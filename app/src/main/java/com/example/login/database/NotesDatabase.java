package com.example.login.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.login.activities.Notes;

@Database(entities = Notes.class, version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract Dao dao();

    public static final String DB_NAME = "notesdatabase";

    private static NotesDatabase instance;
    public static NotesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, NotesDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
