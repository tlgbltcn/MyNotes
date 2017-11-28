package com.example.tolga.mynotes.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tolga.mynotes.Constant;
import com.example.tolga.mynotes.R;

import butterknife.BindView;

/**
 * Created by tolga on 28.11.2017.
 */

public class DetailNoteActivity extends AppCompatActivity{

    @BindView(R.id.notesDetailTitle)
    TextView detailTitle;

    @BindView(R.id.notesDetailDescription)
    TextView detailDescription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.detail_note_activity);
        setContentView(R.layout.activity_detail_note);
        detailTitle.setText(getIntent().getStringExtra(Constant.ITEM_TITLE));
        detailDescription.setText(getIntent().getStringExtra(Constant.ITEM_DESC));
    }

}
