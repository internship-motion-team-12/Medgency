package com.example.medgency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.medgency.model.Hospital;

import java.util.ArrayList;

public class SearchRS extends AppCompatActivity {
    private ArrayList<Hospital> ArrayListHospital;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_rs);
        getData();

        Toolbar ToolbarNavigation = (Toolbar) findViewById(R.id.ToolbarSearchRSNavigation);

        setSupportActionBar(ToolbarNavigation);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getData(){
        ArrayListHospital = new ArrayList<>();
        ArrayListHospital.add(new Hospital("RS Dr. Hasan Sadikin","Kota Bandung","Rumah Sakit Umum"));
        ArrayListHospital.add(new Hospital("RS Muhammadiyah Bandung","Kota Bandung","Rumah Sakit Umum"));
        ArrayListHospital.add(new Hospital("RS Advent Bandung","Kota Bandung","Rumah Sakit Umum"));
        ArrayListHospital.add(new Hospital("RS Dr. HA Rotinsulu","Kota Bandung","Rumah Spesialis paru paru"));
        ArrayListHospital.add(new Hospital("RS Maranatha","Kota Bandung","Rumah Gigi dan Mulut"));
    }
}
