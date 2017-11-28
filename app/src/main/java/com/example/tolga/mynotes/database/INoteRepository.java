package com.example.tolga.mynotes.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by tolga on 28.11.2017.
 */

public interface INoteRepository {

    LiveData<List<Note>> getNotes();

    LiveData<Note> getNote(int id);

    void insertNote(Note note);

    void updateNote(Note note);


}
