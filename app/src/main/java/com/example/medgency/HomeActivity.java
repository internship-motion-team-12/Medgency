package com.example.medgency;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    CardView CV_RS;
    private TextView toolbar_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Typeface mTF = Typeface.createFromAsset(getAssets(),"font/NunitoSans-Bold.ttf");

        Toolbar mToolbar = (Toolbar) findViewById(R.id.ToolbarHome);

        toolbar_text =findViewById(R.id.toolbar_text);
        toolbar_text.setText("Medgency");
        toolbar_text.setTypeface(mTF);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        CV_RS = findViewById(R.id.CVRumahSakit);

        CV_RS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RunActivitySearchRS();
            }
        });
    }

    private void RunActivitySearchRS() {
        Intent i = new Intent(this,SearchRS.class);
        startActivity(i);
    }
}
