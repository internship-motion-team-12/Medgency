package com.example.medgency.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medgency.OnBoard;
import com.example.medgency.R;
import com.example.medgency.SearchRS;

import static android.content.Context.MODE_PRIVATE;

public class ProfilFragment extends Fragment {
    String email_key = "";;
    String email_key_new = "";;
    String EMAIL_KEY = "Email";
    TextView TVLogOut;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        getUsernameLocal();
        TVLogOut = view.findViewById(R.id.TVLogout);

        TVLogOut.setOnClickListener(new View.OnClickListener() {
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
