package com.example.medgency.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medgency.activity.OnBoard;
import com.example.medgency.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class ProfilFragment extends Fragment {
    public static final String TAG = "FrontName";
    String email_key = "";
    String email_key_new = "";
    String EMAIL_KEY = "Email";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        getUsernameLocal();
        RelativeLayout RLSignOut = view.findViewById(R.id.RLSignOut);
        final TextView TVNamaUser = view.findViewById(R.id.TVNamaUser);
        TextView TVToolbarProfil = view.findViewById(R.id.TVToolbarProfil);

        Toolbar ToolbarProfil = view.findViewById(R.id.ToolbarProfil);
        ((AppCompatActivity)getActivity()).setSupportActionBar(ToolbarProfil);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
        TVToolbarProfil.setText("Profil");


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(email_key_new).child("Nama Depan");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String FrontName = dataSnapshot.getValue().toString();
                Log.d(TAG,FrontName);

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("users").child(email_key_new).child("Nama Belakang");
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String BackName = dataSnapshot.getValue().toString();
                        String username = FrontName + " " + BackName;
                        Log.d("USERNAME",username);
                        TVNamaUser.setText(username);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        RLSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // menghapud data username pada local storage (handphone)
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("id", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(email_key,null);
                editor.apply();

                //menuju On Board Act
                Intent intent = new Intent(getActivity(), OnBoard.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("id", MODE_PRIVATE);
        email_key_new = sharedPreferences.getString(email_key,"");
    }
}
