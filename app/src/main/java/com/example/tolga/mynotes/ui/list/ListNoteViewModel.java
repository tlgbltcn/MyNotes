package com.example.tolga.mynotes.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.tolga.mynotes.database.INoteRepository;
import com.example.tolga.mynotes.database.Note;

import java.util.List;

/**
 * Created by tolga on 28.11.2017.
 */

public class ListNoteViewModel extends ViewModel {

    private final INoteRepository noteRepository;

    public ListNoteViewModel(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public LiveData<List<Note>> getNotes() {
        return noteRepository.getNotes();
    }
}
