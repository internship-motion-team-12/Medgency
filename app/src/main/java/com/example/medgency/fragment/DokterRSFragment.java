package com.example.medgency.fragment;

import android.content.Context;
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
import com.example.medgency.adapter.FragmentDokterRSAdapter;

public class DokterRSFragment extends Fragment {
    private Context context;
    private String NamaRS;

    View view;
    public DokterRSFragment() {
    }

    public DokterRSFragment newInstance(Context context1, String NamaRs){
        DokterRSFragment dokterRSFragment = new DokterRSFragment();
        Bundle args = new Bundle();
        NamaRS = NamaRs;
        context = context1;
        return dokterRSFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dokterrs, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new Space(20,1));
        FragmentDokterRSAdapter fragmentDokterRSAdapter = new FragmentDokterRSAdapter(getContext());
        recyclerView.setAdapter(fragmentDokterRSAdapter);
        return view;
    }


}
