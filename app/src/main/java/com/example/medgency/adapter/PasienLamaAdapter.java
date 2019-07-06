package com.example.medgency.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.medgency.R;
import com.example.medgency.ReadArticle;

public class PasienLamaAdapter extends RecyclerView.Adapter<PasienLamaAdapter.PasienLamaViewHolder> {
    Context context;

    public PasienLamaAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PasienLamaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.verifikasi_pasien_lama_item, parent, false);
        return new PasienLamaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PasienLamaViewHolder holder, int i) {
        holder.TVPeringatan.setVisibility(View.GONE);
        holder.BtnGoToVerifikasiPasienLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ReadArticle.class);
                i.putExtra(context.getString(R.string.NomorRekamMedis),holder.ETNoRekamMedis.getText().toString());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class PasienLamaViewHolder extends RecyclerView.ViewHolder {
        TextView TVNoRekamMedis, TVPeringatan;
        EditText ETNoRekamMedis;
        Button BtnGoToVerifikasiPasienLama;
        public PasienLamaViewHolder(@NonNull View itemView) {
            super(itemView);
            TVNoRekamMedis = itemView.findViewById(R.id.TVNama);
            TVPeringatan = itemView.findViewById(R.id.TVPeringatan);
            ETNoRekamMedis = itemView.findViewById(R.id.ETNama);
            BtnGoToVerifikasiPasienLama = itemView.findViewById(R.id.BtnGoToVerifikasiPasienLama);
        }
    }
}
