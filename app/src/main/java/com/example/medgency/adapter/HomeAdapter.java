package com.example.medgency.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medgency.R;
import com.example.medgency.RoundedCornersTransformation;
import com.example.medgency.model.Bacaan;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.BacaanViewHolder> {
    private ArrayList<Bacaan> dataList;
    public Context context;

    public HomeAdapter(Context context, ArrayList<Bacaan> dataList){
        this.context = context;
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
        final Transformation transformation = new RoundedCornersTransformation(30, 0);
        Picasso.with(context).load(dataList.get(position).getUrl_profil()).transform(transformation).placeholder(R.drawable.placeholder).fit().into(holder.IVBacaan);

        holder.TVJudulBacaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        holder.TVPublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent i = new Intent(context,OnBoard.class);
                context.startActivity(i);
                */
            }
        });

        holder.IVBacaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent i = new Intent(context,OnBoard.class);
                context.startActivity(i);
                */
            }
        });
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
