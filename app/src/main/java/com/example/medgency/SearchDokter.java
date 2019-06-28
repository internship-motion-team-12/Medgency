package com.example.medgency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class SearchDokter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dokter);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.ToolbarSearchDokter);

        TextView toolbarText = (TextView) findViewById(R.id.ToolbarTextDokter);
        if(toolbarText!=null && mToolbar!=null) {
            toolbarText.setText(getTitle());
            setSupportActionBar(mToolbar);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
