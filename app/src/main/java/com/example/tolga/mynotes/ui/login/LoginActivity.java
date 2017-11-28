package com.example.tolga.mynotes.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tolga.mynotes.R;
import com.example.tolga.mynotes.databinding.ActivityLoginBinding;
import com.example.tolga.mynotes.ui.list.ListNoteActivity;

/**
 * Created by tolga on 28.11.2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginActivityHandler{

    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.login_activity);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setHandler(this);
    }

    @Override
    public void onLoginClicked(View view) {
        Intent intent = new Intent(this, ListNoteActivity.class);
        startActivity(intent);
    }
}
