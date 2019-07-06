package com.example.medgency;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.medgency.adapter.PasienBaruAdapter;
import com.example.medgency.adapter.PasienLamaAdapter;

public class VerifikasiPasien extends AppCompatActivity {
    RelativeLayout RLJenisPasien;
    TextView TVJnsPasien;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_pasien);

        RLJenisPasien = findViewById(R.id.RLJenisPasien);
        TVJnsPasien = findViewById(R.id.TVJnsPasien);
        RLJenisPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.widget.Toolbar ToolbarNavigation = (Toolbar) findViewById(R.id.ToolbarVerifikasiPasien);

                setSupportActionBar(ToolbarNavigation);
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                TextView toolbarText = (TextView) findViewById(R.id.toolbar_text);
                toolbarText.setText("Verifikasi Pasien");


                final Dialog dialog = new Dialog(VerifikasiPasien.this);
                dialog.setContentView(R.layout.pop_up_patient_type);
                CardView CVPasienLama = dialog.findViewById(R.id.CVPasienLama);
                CardView CVPasienBaru = dialog.findViewById(R.id.CVPasienBaru);

                CVPasienLama.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TVJnsPasien.setText(getString(R.string.PasienLama));
                        PasienLamaAdapter pasienLamaAdapter = new PasienLamaAdapter(getApplicationContext());
                        recyclerView = findViewById(R.id.RLVerifikasiPasien);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(pasienLamaAdapter);
                        dialog.dismiss();
                    }
                });

                CVPasienBaru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TVJnsPasien.setText(getString(R.string.PasienBaru));
                        PasienBaruAdapter pasienBaruAdapter = new PasienBaruAdapter(getApplicationContext(),"RS Hasan Sadikin");
                        recyclerView = findViewById(R.id.RLVerifikasiPasien);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(pasienBaruAdapter);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}
