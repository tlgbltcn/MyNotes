package com.example.tolga.mynotes.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.tolga.mynotes.database.INoteRepository;
import com.example.tolga.mynotes.ui.add.AddNoteViewModel;
import com.example.tolga.mynotes.ui.list.ListNoteViewModel;

/**
 * Created by tolga on 28.11.2017.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final INoteRepository noteRepository;

    /**
     * @param noteRepository ViewModel's repository constructor.
     */

    public ViewModelFactory(INoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * @param modelClass create ViewModel extend from ViewModel.
     */

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(ListNoteViewModel.class)) {
            return modelClass.cast(new ListNoteViewModel(noteRepository));
        } else if (modelClass.isAssignableFrom(AddNoteViewModel.class)) {
            return modelClass.cast(new AddNoteViewModel(noteRepository));
        } else {
            throw new IllegalArgumentException("ViewModel of type " + modelClass + " not found.");
        }
    }
}
