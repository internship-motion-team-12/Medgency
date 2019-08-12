package com.example.medgency.activity;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medgency.R;
import com.example.medgency.adapter.SearchDokterAdapter;
import com.example.medgency.model.Doctor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchDokter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dokter);

        Toolbar mToolbar = findViewById(R.id.ToolbarSearchDokter);
        TextView toolbarText = findViewById(R.id.ToolbarTextDokter);
        TextView TVLihatSemua = findViewById(R.id.TVLihatSemua);
        RelativeLayout RL_sp_umum = findViewById(R.id.RL_sp_umum);
        RelativeLayout RL_sp_anak = findViewById(R.id.RL_sp_anak);
        RelativeLayout RL_sp_gigi = findViewById(R.id.RL_sp_gigi);
        RelativeLayout RL_sp_mata = findViewById(R.id.RL_sp_mata);
        RelativeLayout RL_sp_kandungan = findViewById(R.id.RL_sp_kandungan);
        RelativeLayout RL_sp_tulang = findViewById(R.id.RL_sp_tulang);
        RelativeLayout RL_sp_otak = findViewById(R.id.RL_sp_otak);
        RelativeLayout RL_sp_telinga = findViewById(R.id.RL_sp_telinga);
        final RecyclerView recyclerView = findViewById(R.id.RVSearchDokter);

        final Boolean[] finish_setup_data = {false};
        final ArrayList<Doctor> dataList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Dokter").child("Praktek");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Doctor doctor = dataSnapshot1.getValue(Doctor.class);
                    dataList.add(doctor);
                }
                finish_setup_data[0] = true;
                SearchDokterAdapter searchDokterAdapter = new SearchDokterAdapter(dataList,getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(searchDokterAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(),"Tidak dapat terhubung ke internet, periksa koneksi anda",Toast.LENGTH_LONG).show();
            }
        });

        if(toolbarText!=null && mToolbar!=null) {
            toolbarText.setText(getTitle());
            setSupportActionBar(mToolbar);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        Doctor doctor = new Doctor();
        doctor.setName("Agus");
        doctor.setSpecialist("Umum");
        doctor.setImg_url("https://firebasestorage.googleapis.com/v0/b/medgency.appspot.com/o/Foto%20Dokter%2Fumum%2Fdr%20Dickson%20Tan%2Fprofil.jpg?alt=media&token=c3d96ab9-d3ed-44bc-8378-0c3d6ec66b4c");
        dataList.add(doctor);
        */
        //dataList.remove(0);

        final SearchDokterAdapter searchDokterAdapter = new SearchDokterAdapter(dataList,getApplicationContext());

        RL_sp_umum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finish_setup_data[0]){
                    int size = dataList.size()-1;
                    int i = 0;
                    ArrayList<Doctor> dataList2 = new ArrayList<>();
                    if (size > 0){
                        do{
                            if (dataList.get(i).getSpecialist().equals("umum")){
                                dataList2.add(dataList.get(i));
                            }
                            if (i == size-1){
                                break;
                            }
                            i++;
                        }while (true);
                    }
                    SearchDokterAdapter searchDokterAdapter1 = new SearchDokterAdapter(dataList2,getApplicationContext());
                    recyclerView.setAdapter(searchDokterAdapter);
                }
            }
        });

        RL_sp_anak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finish_setup_data[0]){
                    int size = dataList.size()-1;
                    int i = 0;
                    ArrayList<Doctor> dataList2 = new ArrayList<>();
                    if (size > 0){
                        do{
                            if (dataList.get(i).getSpecialist().equals("anak")){
                                dataList2.add(dataList.get(i));
                            }
                            if (i == size-1){
                                break;
                            }
                            i++;
                        }while (true);
                    }
                    SearchDokterAdapter searchDokterAdapter1 = new SearchDokterAdapter(dataList2,getApplicationContext());
                    recyclerView.setAdapter(searchDokterAdapter);
                }
            }
        });

        RL_sp_gigi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finish_setup_data[0]){
                    int size = dataList.size()-1;
                    int i = 0;
                    ArrayList<Doctor> dataList2 = new ArrayList<>();
                    if (size > 0){
                        do{
                            if (dataList.get(i).getSpecialist().equals("gigi")){
                                dataList2.add(dataList.get(i));
                            }
                            if (i == size-1){
                                break;
                            }
                            i++;
                        }while (true);
                    }
                    SearchDokterAdapter searchDokterAdapter1 = new SearchDokterAdapter(dataList2,getApplicationContext());
                    recyclerView.setAdapter(searchDokterAdapter);
                }
            }
        });

        RL_sp_mata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finish_setup_data[0]){
                    int size = dataList.size()-1;
                    int i = 0;
                    ArrayList<Doctor> dataList2 = new ArrayList<>();
                    if (size > 0){
                        do{
                            if (dataList.get(i).getSpecialist().equals("mata")){
                                dataList2.add(dataList.get(i));
                            }
                            if (i == size-1){
                                break;
                            }
                            i++;
                        }while (true);
                    }
                    SearchDokterAdapter searchDokterAdapter1 = new SearchDokterAdapter(dataList2,getApplicationContext());
                    recyclerView.setAdapter(searchDokterAdapter);
                }
            }
        });

        RL_sp_kandungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finish_setup_data[0]){
                    int size = dataList.size()-1;
                    int i = 0;
                    ArrayList<Doctor> dataList2 = new ArrayList<>();
                    if (size > 0){
                        do{
                            if (dataList.get(i).getSpecialist().equals("kandungan")){
                                dataList2.add(dataList.get(i));
                            }
                            if (i == size-1){
                                break;
                            }
                            i++;
                        }while (true);
                    }
                    SearchDokterAdapter searchDokterAdapter1 = new SearchDokterAdapter(dataList2,getApplicationContext());
                    recyclerView.setAdapter(searchDokterAdapter);
                }
            }
        });

        RL_sp_tulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finish_setup_data[0]){
                    int size = dataList.size()-1;
                    int i = 0;
                    ArrayList<Doctor> dataList2 = new ArrayList<>();
                    if (size > 0){
                        do{
                            if (dataList.get(i).getSpecialist().equals("tulang")){
                                dataList2.add(dataList.get(i));
                            }
                            if (i == size-1){
                                break;
                            }
                            i++;
                        }while (true);
                    }
                    SearchDokterAdapter searchDokterAdapter1 = new SearchDokterAdapter(dataList2,getApplicationContext());
                    recyclerView.setAdapter(searchDokterAdapter);
                }
            }
        });

        RL_sp_otak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finish_setup_data[0]){
                    int size = dataList.size()-1;
                    int i = 0;
                    ArrayList<Doctor> dataList2 = new ArrayList<>();
                    if (size > 0){
                        do{
                            if (dataList.get(i).getSpecialist().equals("otak")){
                                dataList2.add(dataList.get(i));
                            }
                            if (i == size-1){
                                break;
                            }
                            i++;
                        }while (true);
                    }
                    SearchDokterAdapter searchDokterAdapter1 = new SearchDokterAdapter(dataList2,getApplicationContext());
                    recyclerView.setAdapter(searchDokterAdapter);
                }
            }
        });

        RL_sp_telinga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finish_setup_data[0]){
                    int size = dataList.size()-1;
                    int i = 0;
                    ArrayList<Doctor> dataList2 = new ArrayList<>();
                    if (size > 0){
                        do{
                            if (dataList.get(i).getSpecialist().equals("telinga")){
                                dataList2.add(dataList.get(i));
                            }
                            if (i == size-1){
                                break;
                            }
                            i++;
                        }while (true);
                    }
                    SearchDokterAdapter searchDokterAdapter1 = new SearchDokterAdapter(dataList2,getApplicationContext());
                    recyclerView.setAdapter(searchDokterAdapter);
                }
            }
        });

        TVLihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SearchDokter.this);
                dialog.setContentView(R.layout.pop_up_specialist);

                ImageView btn_close_dialog = dialog.findViewById(R.id.btn_close_dialog);

                btn_close_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}
