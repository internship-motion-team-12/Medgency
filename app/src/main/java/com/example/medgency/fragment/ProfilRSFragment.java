package com.example.medgency.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medgency.R;
import com.example.medgency.Space;
import com.example.medgency.adapter.ProfilRSFragmentAdapter;

public class ProfilRSFragment extends Fragment {
    View view;
    public ProfilRSFragment() {
    }

    public static ProfilRSFragment newInstance(){
        ProfilRSFragment profilRSFragment = new ProfilRSFragment();
        Bundle args = new Bundle();
        profilRSFragment.setArguments(args);
        return profilRSFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profilrs, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new Space(20,1));
        recyclerView.setAdapter(new ProfilRSFragmentAdapter(getContext()));
        return view;
    }
}
