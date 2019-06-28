package com.example.medgency;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.medgency.adapter.HomeAdapter;
import com.example.medgency.adapter.ViewPagerAdapter;
import com.example.medgency.model.Bacaan;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private CardView CV_RS;
    private TextView toolbar_text;
    private ViewPager viewPager;
    private Handler handler;
    private int delay = 8000;
    private int page = 0;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private ArrayList<Bacaan> ArrayListBacaan;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    Runnable runnable = new Runnable() {
        public void run() {
            if (viewPagerAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addDataBacaan();
        recyclerView = findViewById(R.id.RecyclerViewArticleHome);
        homeAdapter = new HomeAdapter(ArrayListBacaan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        viewPager.setAdapter(viewPagerAdapter);

        handler = new Handler();

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            if (i == 0){
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }
            else{
                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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

    void addDataBacaan(){
        ArrayListBacaan = new ArrayList<>();
        ArrayListBacaan.add(new Bacaan("Judul 1", "publisher"));
        ArrayListBacaan.add(new Bacaan("Judul 2", "publisher"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}
