package com.example.medgency.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medgency.R;
import com.squareup.picasso.Picasso;

public class IkonJenisKelaminAdapter extends RecyclerView.Adapter<IkonJenisKelaminAdapter.ViewHoldder> {
    private int selectedPos = RecyclerView.NO_POSITION;
    private Context context;

    public IkonJenisKelaminAdapter(Context context1){
        context = context1;
    }

    @NonNull
    @Override
    public ViewHoldder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.item_ikon_jenis_kelamin,viewGroup,false);
        return new IkonJenisKelaminAdapter.ViewHoldder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldder viewHoldder, final int i) {
        if (i == 0){
            Picasso.with(context).load("https://firebasestorage.googleapis.com/v0/b/medgency.appspot.com/o/ikon%2Flaki_laki.png?alt=media&token=2db09798-f059-42b9-9033-74e709afeab7").into(viewHoldder.imageView);
            viewHoldder.textView.setText("Laki - laki");
        }
        else{
            Picasso.with(context).load("https://firebasestorage.googleapis.com/v0/b/medgency.appspot.com/o/ikon%2Fperempuan.png?alt=media&token=2d829a34-ac0e-4a5c-b0cc-a92b9a3e6f4a").into(viewHoldder.imageView);
            viewHoldder.textView.setText("Perempuan");
        }
        viewHoldder.itemView.setSelected(selectedPos == i);
        viewHoldder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(selectedPos);
                selectedPos = i;
                notifyItemChanged(selectedPos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHoldder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        ConstraintLayout constraintLayout;
        public ViewHoldder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
