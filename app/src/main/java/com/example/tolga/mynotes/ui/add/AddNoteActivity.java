package com.example.tolga.mynotes.ui.add;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.example.tolga.mynotes.MyNotesApplication;
import com.example.tolga.mynotes.R;
import com.example.tolga.mynotes.database.Note;
import com.example.tolga.mynotes.database.NoteRepository;
import com.example.tolga.mynotes.databinding.ActivityAddNoteBinding;
import com.example.tolga.mynotes.ui.ViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tolga on 28.11.2017.
 */

public class AddNoteActivity extends AppCompatActivity implements LifecycleRegistryOwner,AddNoteActivityHandler {

    private static final String NOTE_ID = "NOTE_ID";

    private ActivityAddNoteBinding activityAddNoteBinding;

    private final LifecycleRegistry registry = new LifecycleRegistry(this);

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.noteTitleEditText) TextInputEditText noteTitleEditText;
    @BindView(R.id.noteValueEditText) TextInputEditText noteValueEditText;

    private int noteId;

    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddNoteActivity.class);
        context.startActivity(starter);
    }
    public static void start(Context context, int id) {
        Intent starter = new Intent(context, AddNoteActivity.class);
        starter.putExtra(NOTE_ID, id);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.add_note_activity);
        activityAddNoteBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_note);
        ButterKnife.bind(this);
        activityAddNoteBinding.setHandler(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("New Note");


        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getExtras().containsKey(NOTE_ID)) {
            noteId = intent.getIntExtra(NOTE_ID, 0);


            // Dependency injection
            ViewModelFactory viewModelFactory = new ViewModelFactory(new NoteRepository(MyNotesApplication.getDatabase().noteDao()));

            AddNoteViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddNoteViewModel.class);
            viewModel.getNote(noteId).observe(this, new Observer<Note>() {
                @Override
                public void onChanged(@Nullable Note note) {
                    if (note != null) {
                        noteTitleEditText.setText(note.getTitle());
                        noteValueEditText.setText(note.getValue());
                    }
                }
            });
        }

    }


    @Override
    public void onSavedCicked(View view) {
                // Insert note
                if (noteId == 0) {
                    Note note = new Note(noteTitleEditText.getText().toString(), noteValueEditText.getText().toString());
                    new AsyncTask<Note, Void, Void>() {
                        @Override
                        protected Void doInBackground(Note... notes) {
                            MyNotesApplication.getDatabase().noteDao().insertNote(notes[0]);
                            return null;
                        }
                    }.execute(note);
                    Toast.makeText(this, "Note inserted!", Toast.LENGTH_SHORT).show();
                } else {
                    // Update note
                    Note note = new Note(noteId, noteTitleEditText.getText().toString(), noteValueEditText.getText().toString());
                    new AsyncTask<Note, Void, Void>() {
                        @Override
                        protected Void doInBackground(Note... notes) {
                            MyNotesApplication.getDatabase().noteDao().updateNote(notes[0]);
                            return null;
                        }
                    }.execute(note);
                    Toast.makeText(this, "Note updated!", Toast.LENGTH_SHORT).show();
                }

                finish();
        }

    }

