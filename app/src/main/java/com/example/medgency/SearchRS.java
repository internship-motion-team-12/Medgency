package com.example.medgency;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;

import com.example.medgency.adapter.SearchRSActAdapter;
import com.example.medgency.model.Hospital;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchRS extends AppCompatActivity {
    private ArrayList<Hospital> ArrayListHospital;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_rs);

        recyclerView = findViewById(R.id.RecyclerViewSearchRS);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Rumah Sakit");
        databaseReference.keepSynced(true);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayListHospital = new ArrayList<Hospital>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Hospital p = dataSnapshot1.getValue(Hospital.class);
                    ArrayListHospital.add(p);
                }
                SearchRSActAdapter searchRSActAdapter = new SearchRSActAdapter(getApplicationContext(),ArrayListHospital);
                //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                //recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(searchRSActAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Toolbar ToolbarNavigation = (Toolbar) findViewById(R.id.ToolbarSearchRSNavigation);

        setSupportActionBar(ToolbarNavigation);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
