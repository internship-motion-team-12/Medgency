package com.example.medgency.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medgency.R;
import com.example.medgency.SearchRS;
import com.example.medgency.adapter.HomeAdapter;
import com.example.medgency.adapter.ViewPagerAdapter;
import com.example.medgency.model.Bacaan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    Handler h = new Handler();
    RecyclerView recyclerView;
    private Toolbar mToolbar;
    private ArrayList<Bacaan> ArrayListBacaan;
    TextView toolbar_text;
    CardView cardView;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    private int delay = 8000;
    int page = 0;
    private ViewPager viewPager;
    private Handler handler;
    Runnable runnable;
    private int[] pagerIndex = {0};
    private DatabaseReference reference;

    public String judul;
    public String publisher;
    public String uri;

    ArrayList<Bacaan> list;
    HomeAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final Context context = getActivity();
        recyclerView = view.findViewById(R.id.RecyclerViewArticleHome);


        reference = FirebaseDatabase.getInstance().getReference().child("Bacaan").child("Menarik");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Bacaan>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Bacaan p = dataSnapshot1.getValue(Bacaan.class);
                    list.add(p);
                }
                homeAdapter = new HomeAdapter(getContext(),list);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(homeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        cardView = view.findViewById(R.id.CVRumahSakit);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchRS.class);
                startActivity(intent);
            }
        });

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getContext()));

        //DotsIndicator dotsIndicator = (DotsIndicator) view.findViewById(R.id.dots_indicator);
        WormDotsIndicator dotsIndicator = (WormDotsIndicator) view.findViewById(R.id.dots_indicator);
        dotsIndicator.setViewPager(viewPager);


        h.postDelayed(new Runnable() {
            public void run() {
                pagerIndex[0]++;
                if (pagerIndex[0] >= 6) {
                    pagerIndex[0] = 0;
                }

                viewPager.setCurrentItem(pagerIndex[0]);
                runnable=this;

                h.postDelayed(runnable, delay);
            }
        }, delay);

        //sliderDotspanel = (LinearLayout) view.findViewById(R.id.SliderDots) ;

        handler = new Handler();
        /*
        dotscount = 6;
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getContext());
            if (i == 0){
                dots[i] = new ImageView(getContext());
                dots[i].setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()).getApplicationContext(), R.drawable.active_dot));
            }
            else{
                //dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.non_active_dot));
                dots[i] = new ImageView(getContext());
                dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.non_active_dot));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);
        }
        */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
                pagerIndex[0] = position;
                for(int i = 0; i< dotscount; i++){
                    //dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.non_active_dot));
                    //dots[i].setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()).getApplicationContext(), R.drawable.non_active_dot));
                    // ketiga dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.non_active_dot));
                }
                // kedua dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));
                //dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mToolbar = (Toolbar) view.findViewById(R.id.ToolbarHome);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar_text = view.findViewById(R.id.toolbar_text);
        toolbar_text.setText("Medgency");
        return view;
    }
}
