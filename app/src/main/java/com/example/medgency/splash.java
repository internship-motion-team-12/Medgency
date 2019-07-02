package com.example.medgency;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    String email_key = "";
    String email_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUsernameLocal();
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences("id", MODE_PRIVATE);
        email_key_new = sharedPreferences.getString(email_key,"");
        if (email_key_new.isEmpty()){
            Intent intent = new Intent(this, OnBoard.class);
            intent.putExtra("Status","Dont destroy");
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Status","Dont destroy");
            startActivity(intent);
            finish();
        }
    }
}
