package com.example.medgency;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.widget.TextView;

public class TiketReservasi extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket_reservasi);

        toolbar = (Toolbar) findViewById(R.id.ToolbarTiketReservasi);

        TextView toolbar_text = findViewById(R.id.toolbar_text);
        toolbar_text.setText("Tiket Reservasi");
        setSupportActionBar(toolbar);
    }
}
