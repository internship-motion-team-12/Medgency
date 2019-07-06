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

import com.example.medgency.PilihJadwal;
import com.example.medgency.R;

public class PasienBaruAdapter extends RecyclerView.Adapter<PasienBaruAdapter.PasienBaruViewHolder> {
    private Context context;
    private String nama_rs;

    public PasienBaruAdapter(Context context, String nama_rs) {
        this.context = context;
        this.nama_rs = nama_rs;
    }

    @NonNull
    @Override
    public PasienBaruViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.verifikasi_pasien_baru_item,parent,false);
        return new PasienBaruViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasienBaruViewHolder holder, int i) {
        holder.TVPeringatanNama.setVisibility(View.GONE);
        holder.TVPeringatanNIK.setVisibility(View.GONE);
        holder.TVPeringatanTTL.setVisibility(View.GONE);
        holder.TVPeringatanAlamat.setVisibility(View.GONE);
        holder.TVPeringatanAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PilihJadwal.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class PasienBaruViewHolder extends RecyclerView.ViewHolder {
        EditText ETNama, ETNIK, ETTTL, ETAlamat;
        TextView TVPeringatanNama, TVPeringatanNIK, TVPeringatanTTL, TVPeringatanAlamat;
        Button BtnGoToPilihJadwal;

        public PasienBaruViewHolder(@NonNull View itemView) {
            super(itemView);
            ETNama = itemView.findViewById(R.id.ETNama);
            ETNIK = itemView.findViewById(R.id.ETNIK);
            ETTTL = itemView.findViewById(R.id.ETTTL);
            ETAlamat = itemView.findViewById(R.id.ETAlamat);
            BtnGoToPilihJadwal = itemView.findViewById(R.id.BtnGoToPilihJadwal);

            TVPeringatanNama = itemView.findViewById(R.id.TVPeringatanNama);
            TVPeringatanNIK = itemView.findViewById(R.id.TVPeringatanNIK);
            TVPeringatanTTL = itemView.findViewById(R.id.TVPeringatanTTL);
            TVPeringatanAlamat = itemView.findViewById(R.id.TVPeringatanAlamat);
        }
    }
}
