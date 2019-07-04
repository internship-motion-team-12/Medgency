package com.example.medgency;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.example.medgency.adapter.ViewPagerProfileRSAdapter;
import com.example.medgency.fragment.DokterRSFragment;
import com.example.medgency.fragment.LayananFragment;
import com.example.medgency.fragment.ProfilFragment;
import com.example.medgency.fragment.ProfilRSFragment;
import com.example.medgency.model.Hospital;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfilRS extends AppCompatActivity {
    private ImageView IVRS;
    private TextView TVNamaRS, TVAdressRS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_rs);

        IVRS = findViewById(R.id.IVRS);
        TVNamaRS = findViewById(R.id.TVNamaRS);
        TVAdressRS = findViewById(R.id.TVAdressRS);

        Intent fromSearchRS = getIntent();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Rumah Sakit").child(DeleteDotFromString(fromSearchRS.getStringExtra("nama")));
        databaseReference.keepSynced(true);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Hospital p = dataSnapshot.getValue(Hospital.class);
                TVNamaRS.setText(p.getNama());
                TVAdressRS.setText(p.getAddress());
                Picasso.with(getApplicationContext()).load(p.getUrl_profil()).fit().into(IVRS);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ImageView IVToGoogleMaps = findViewById(R.id.IVToGoogleMaps);
        final String url = fromSearchRS.getStringExtra("url_map");
        IVToGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri Coordinate = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,Coordinate);
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });

        // setting view pager
        ViewPager viewPager = findViewById(R.id.VP_container_profil_rs);
        ViewPagerProfileRSAdapter viewPagerProfileRSAdapter = new ViewPagerProfileRSAdapter(getSupportFragmentManager());
        viewPagerProfileRSAdapter.AddFragment(new ProfilRSFragment(), "Profil RS");
        viewPagerProfileRSAdapter.AddFragment(new LayananFragment(), "layanan");
        viewPagerProfileRSAdapter.AddFragment(new DokterRSFragment(), "Dokter");
        viewPager.setAdapter(viewPagerProfileRSAdapter);

        // setting tabLayout
        XTabLayout xTabLayout = findViewById(R.id.XTabLayout);
        xTabLayout.setupWithViewPager(viewPager);
    }

    private String DeleteDotFromString(String string){
        int i = 0;
        String temp = "";
        while (i < string.length()){
            if (!(string.charAt(i) == '.')){
                temp = temp + string.charAt(i);
            }
            i++;
        }
        return temp;
    }
}
