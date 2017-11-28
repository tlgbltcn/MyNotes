package com.example.tolga.mynotes.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by tolga on 28.11.2017.
 */
@Entity
public class Note implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String value;

    /**
     * Constructor to create a new active Note.
     * @param id          id of RoomDatabase primaryKey
     * @param title       title of the note
     * @param value description of the note
     */

    public Note(int id, String title, String value) {
        this.id = id;
        this.title = title;
        this.value = value;
    }

    @Ignore
    public Note(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }
}
