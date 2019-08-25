package com.example.medgency.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medgency.R;
import com.squareup.picasso.Picasso;

public class Klinik extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klinik);

        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView TVToolbar = findViewById(R.id.TVToolbar);
        TVToolbar.setText("Klinik");

        ImageView IV = findViewById(R.id.IV);
        Picasso.with(Klinik.this).load("https://firebasestorage.googleapis.com/v0/b/medgency.appspot.com/o/ikon%2Fundraw_under_construction_46pa.png?alt=media&token=e4f42c74-bf3d-401a-a015-8a324554a8e4").into(IV);
    }
}
