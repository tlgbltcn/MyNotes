package com.example.tolga.mynotes.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by tolga on 28.11.2017.
 */
@Database(entities = {Note.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
}
