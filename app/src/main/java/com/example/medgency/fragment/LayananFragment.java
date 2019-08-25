package com.example.medgency.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medgency.R;
import com.example.medgency.Space;
import com.example.medgency.adapter.FragmentLayananAdapter;

import static android.content.Context.MODE_PRIVATE;

public class LayananFragment extends Fragment {
    private Context context;
    private String NamaRS;

    public LayananFragment newInstance(Context context1, String NamaRS1){
        LayananFragment layananFragment = new LayananFragment();
        Bundle args = new Bundle();
        layananFragment.setArguments(args);
        NamaRS = NamaRS1;
        context = context1;
        return  layananFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layanan, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new Space(20,1));
        recyclerView.setAdapter(new FragmentLayananAdapter(getContext(),"RS Advent Bandung"));
        return view;
    }
}
