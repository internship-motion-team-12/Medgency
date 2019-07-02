package com.example.medgency.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medgency.R;
import com.example.medgency.fragment.HomeFragment;
import com.example.medgency.model.Bacaan;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.BacaanViewHolder> {
    private ArrayList<Bacaan> dataList;

    public HomeAdapter(ArrayList<Bacaan> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public HomeAdapter.BacaanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.bacaan_item, parent, false);
        return new BacaanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.BacaanViewHolder holder, int position) {
        holder.TVJudulBacaan.setText(dataList.get(position).getJudul());
        holder.TVPublisher.setText(dataList.get(position).getPublisher());
        //Picasso.load(dataList.get(position).getUrl_profil()).into(holder.IVBacaan);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class BacaanViewHolder extends RecyclerView.ViewHolder {
        private TextView TVJudulBacaan, TVPublisher;
        private ImageView IVBacaan;
        public BacaanViewHolder(@NonNull View itemView) {
            super(itemView);
            TVJudulBacaan = itemView.findViewById(R.id.TVJudulBacaan);
            TVPublisher = itemView.findViewById(R.id.TVPublisher);
            IVBacaan = itemView.findViewById(R.id.IVBacaan);
        }
    }
}
