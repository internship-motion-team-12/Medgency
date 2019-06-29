package com.example.medgency.adapter;

import android.graphics.drawable.AdaptiveIconDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.medgency.R;
import com.example.medgency.model.Hospital;

import java.util.ArrayList;

public class SearchRSActAdapter extends RecyclerView.Adapter<SearchRSActAdapter.HospitalViewHolder> {
    private ArrayList<Hospital> dataList;
    private LayoutInflater mInflater;
    //private ItemClickListener mClickListener;

    public SearchRSActAdapter (ArrayList<Hospital> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.searchrs_item, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder hospitalViewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder {
        TextView TVNamaRS_RecyclerView, TVJenisRS, TVAddress;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            TVNamaRS_RecyclerView = itemView.findViewById(R.id.TVNamaRS_RecyclerView);
            TVJenisRS = itemView.findViewById(R.id.TVJenisRS);
            TVAddress = itemView.findViewById(R.id.TVAddress);
        }
    }
}
