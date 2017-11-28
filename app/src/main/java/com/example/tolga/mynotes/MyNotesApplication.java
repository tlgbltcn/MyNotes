package com.example.tolga.mynotes;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.tolga.mynotes.database.AppDatabase;

/**
 * Created by tolga on 28.11.2017.
 */

public class MyNotesApplication extends Application {


    /**
     * @param database Create Database here at Application.
     */

    private static AppDatabase database;


    @Override
    public void onCreate() {
        super.onCreate();

        if (database == null) {
            database = Room.databaseBuilder(this, AppDatabase.class, "note-database").build();
        }
    }

    public static AppDatabase getDatabase() {
        return database;
    }



}
