package com.example.medgency.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medgency.R;
import com.example.medgency.adapter.JadwalAdapter;
import com.example.medgency.model.Date;
import com.example.medgency.model.Doctor;
import com.example.medgency.model.Jadwal;
import com.example.medgency.model.Ticket;
import com.example.medgency.model.Time;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class JadwalSayaFragment extends Fragment {
    private JadwalAdapter adapter;

    String email_key = "";
    String email_key_new = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_jadwal_saya, container, false);
        final Context context = getActivity();

        getUsernameLocal();
        final RecyclerView recyclerView = view.findViewById(R.id.RecyclerViewListJadwal);
        Typeface customfont=Typeface.createFromAsset(context.getAssets(),"font/NunitoSans-Bold.ttf");

        Toolbar toolbar = view.findViewById(R.id.ToolbarJadwalSaya);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        TextView TVToolbar = view.findViewById(R.id.TVToolbar);
        TVToolbar.setText(getString(R.string.JadwalSaya));
        TVToolbar.setTypeface(customfont);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Tiket").child(email_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Ticket> dataList = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Ticket ticket = dataSnapshot1.getValue(Ticket.class);
                    dataList.add(ticket);
                }
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                //Set adapter
                adapter = new JadwalAdapter(dataList,getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("id", MODE_PRIVATE);
        email_key_new = sharedPreferences.getString(email_key,"");
    }
}
