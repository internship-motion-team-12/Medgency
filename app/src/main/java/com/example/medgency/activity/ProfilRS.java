package com.example.medgency.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.example.medgency.R;
import com.example.medgency.FungsiFungsi.StringFunction;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilRS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_rs);

        Intent FromSearchRS = getIntent();
        String nama = FromSearchRS.getStringExtra("nama");

        final Toolbar toolbar = findViewById(R.id.ToolbarProfilRS);
        TextView TVNamaRS = findViewById(R.id.TVNamaRS);
        TextView TVAlamatRS = findViewById(R.id.TVAlamatRS);
        TextView TVToolbar = findViewById(R.id.TVToolbar);
        XTabLayout xTabLayout = findViewById(R.id.xTabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TVToolbar.setText(nama);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Rumah Sakit").child(StringFunction.DeleteDotFromString(nama));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
