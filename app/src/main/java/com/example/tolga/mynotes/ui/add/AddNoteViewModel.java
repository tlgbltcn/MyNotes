package com.example.tolga.mynotes.ui.add;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.tolga.mynotes.database.INoteRepository;
import com.example.tolga.mynotes.database.Note;

/**
 * Created by tolga on 28.11.2017.
 */

public class AddNoteViewModel extends ViewModel {

    private final INoteRepository noteRepository;

    public AddNoteViewModel(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    LiveData<Note> getNote(int noteId) {
        return noteRepository.getNote(noteId);
    }
}
