package com.example.medgency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class VerifikasiPasien extends AppCompatActivity {
    RelativeLayout RLJenisPasien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_pasien);

        RLJenisPasien = findViewById(R.id.RLJenisPasien);

        RLJenisPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }
}
