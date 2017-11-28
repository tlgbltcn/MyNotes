package com.example.tolga.mynotes.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by tolga on 28.11.2017.
 */

public class NoteRepository implements INoteRepository {

    private final NoteDao NoteDao;

    public NoteRepository(NoteDao noteDao) {
        NoteDao = noteDao;
    }


    @Override
    public LiveData<List<Note>> getNotes() {
        return NoteDao.getNotes();
    }

    @Override
    public LiveData<Note> getNote(int id) {
        return NoteDao.getNote(id);
    }

    @Override
    public void insertNote(Note note) {
    }

    @Override
    public void updateNote(Note note) {
    }


}
