package com.example.tolga.mynotes.ui.list;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.tolga.mynotes.MyNotesApplication;
import com.example.tolga.mynotes.R;
import com.example.tolga.mynotes.database.Note;
import com.example.tolga.mynotes.database.NoteRepository;
import com.example.tolga.mynotes.ui.ViewModelFactory;
import com.example.tolga.mynotes.ui.add.AddNoteActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tolga on 28.11.2017.
 */

public class ListNoteActivity extends AppCompatActivity implements LifecycleRegistryOwner {
    private static final String TAG = ListNoteActivity.class.getCanonicalName();

    private final LifecycleRegistry registry = new LifecycleRegistry(this);

    private ListNoteAdapter noteAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.list_note_activity);
        setContentView(R.layout.activity_list_note);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("My Notes");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new ListNoteAdapter();
        noteAdapter.setOnNoteClickListener(new ListNoteAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                openNoteDetail(note.getId());
            }
        });
        recyclerView.setAdapter(noteAdapter);

        // Dependency injection
        ViewModelFactory viewModelFactory = new ViewModelFactory(new NoteRepository(MyNotesApplication.getDatabase().noteDao()));

        ListNoteViewModel viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListNoteViewModel.class);
        viewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                noteAdapter.setNotes(notes);
            }
        });
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        openNoteDetail();
    }

    private void openNoteDetail() {
        AddNoteActivity.start(this);
    }

    private void openNoteDetail(int noteId) {
        AddNoteActivity.start(this, noteId);
    }
}
