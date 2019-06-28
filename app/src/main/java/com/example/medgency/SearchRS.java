package com.example.medgency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class SearchRS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_rs);

        Toolbar ToolbarNavigation = (Toolbar) findViewById(R.id.ToolbarSearchRSNavigation);

        setSupportActionBar(ToolbarNavigation);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
