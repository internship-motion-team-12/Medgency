package com.example.medgency.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.medgency.R;
import com.example.medgency.activity.TiketReservasi;
import com.example.medgency.model.Jadwal;
import com.example.medgency.model.Ticket;

import java.util.ArrayList;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder> {
    private ArrayList<Ticket> dataList;
    public Context context;

    public JadwalAdapter(ArrayList<Ticket> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public JadwalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_schedule_list, parent, false);
        return new JadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final JadwalViewHolder holder, final int i) {
        final Ticket itemList = dataList.get(i);
        holder.TVNamaRS.setText(dataList.get(i).getPenyedia_layanan());
        holder.TVDate.setText(dataList.get(i).getTanggal());
        holder.TVTime.setText(dataList.get(i).getWaktu());
        holder.IVOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.IVOption);
                popupMenu.inflate(R.menu.option_schedule_list);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_lihat_detail :
                                Intent goToTiketReservasi = new Intent(context, TiketReservasi.class);
                                goToTiketReservasi.putExtra(context.getString(R.string.IdTicket),dataList.get(i).getId());
                                context.startActivity(goToTiketReservasi);
                                break;
                            case R.id.menu_cetak_tiket :
                                //
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class JadwalViewHolder extends RecyclerView.ViewHolder {
        public TextView TVNamaRS;
        public TextView TVDate;
        public TextView TVTime;
        public ImageView IVOption;

        public JadwalViewHolder(@NonNull View itemView) {
            super(itemView);
            TVNamaRS = itemView.findViewById(R.id.TVNamaRS);
            TVDate = itemView.findViewById(R.id.TVDate);
            TVTime = itemView.findViewById(R.id.TVTime);
            IVOption = itemView.findViewById(R.id.IVOption);
        }
    }
}
