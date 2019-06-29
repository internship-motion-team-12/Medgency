package com.example.medgency;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medgency.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    private TextView TV1,TV2,TV3;
    private EditText ETNamDep, ETNamBel;
    private String JnsKelamin;
    private Button mButton;
    private ImageView IVPria, IVWanita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.ToolbarSignUp);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TV1 = findViewById(R.id.TVDaftarSignUp);
        TV2 = findViewById(R.id.TVMasukanBiodataAnda);
        TV3 = findViewById(R.id.TVJenisKelamin);
        mButton = findViewById(R.id.ButtonSignUp);
        ETNamDep = findViewById(R.id.ETNamaDepan);
        ETNamBel = findViewById(R.id.ETNamaBelakang);
        JnsKelamin = getString(R.string.BlmDiSet);
        IVPria = findViewById(R.id.IVPria);
        IVWanita = findViewById(R.id.IVWanita);

        Typeface mTF = Typeface.createFromAsset(getAssets(),"font/NunitoSans-Regular.ttf");

        TV1.setTypeface(mTF);
        TV2.setTypeface(mTF);
        TV3.setTypeface(mTF);
        mButton.setTypeface(mTF);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JnsKelamin = "pria";
                if (isValidName(ETNamDep.getText().toString()) && isValidName(ETNamDep.getText().toString()) && (JnsKelamin == "pria" || JnsKelamin=="wanita") ){
                    User user = new User(ETNamDep.getText().toString(), ETNamBel.getText().toString(), JnsKelamin);
                    RunActivitySignUpEmail(user);
                }
                else{
                    MakeWarningToast();
                }
            }
        };

        View.OnClickListener listenerIVPria = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UbahWarnaPria();
            }
        };
        View.OnClickListener listenerIVWanita = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UbahWarnaWanita();
            }
        };

        mButton.setOnClickListener(listener);
        IVPria.setOnClickListener(listenerIVPria);
        IVWanita.setOnClickListener(listenerIVWanita);
        ETNamDep.addTextChangedListener(TWNamDep);
        ETNamBel.addTextChangedListener(TWNamBel);
    }

    private void MakeWarningToast() {
        if (ETNamDep.getText().toString().isEmpty()){
            Toast.makeText(this,"Nama depan belum diisi",Toast.LENGTH_SHORT).show();
        }
        else if (isStringContainNumber(ETNamDep.getText().toString()) || isStringContainNumber(ETNamBel.getText().toString()) ){
            Toast.makeText(this,"Nama tidak boleh mengandung angka",Toast.LENGTH_SHORT).show();
        }
        else if (ETNamBel.getText().toString().isEmpty()){
            Toast.makeText(this,"Nama belakang belum diisi",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Nama tidak boleh mengandung angka",Toast.LENGTH_SHORT).show();
        }
    }

    private void RunActivitySignUpEmail(User user) {
        Intent i = new Intent(this,SignUpEmail.class);
        i.putExtra(getString(R.string.NamaDepan),user.getNamaDepan());
        i.putExtra(getString(R.string.NamaBelakang),user.getNamaBelakang());
        i.putExtra(getString(R.string.JenisKelamin),user.getJenisKelamin());
        startActivity(i);
    }

    private void UbahWarnaPria(){
        if (IVPria.getSolidColor() == Color.parseColor("#808080")){
            if (JnsKelamin == "wanita"){
                IVPria.setBackgroundColor(Color.parseColor("#006EFD"));
            }
            JnsKelamin = "pria";
            IVWanita.setBackgroundColor(Color.parseColor("#808080"));
        }
    }

    private void UbahWarnaWanita(){
        if (IVWanita.getSolidColor() == Color.parseColor("#808080")){
            if (JnsKelamin == "pria"){
                IVPria.setBackgroundColor(Color.parseColor("#808080"));
            }
            JnsKelamin = "wanita";
            IVWanita.setBackgroundColor(Color.parseColor("#006EFD"));
        }
    }

    private Boolean isValidName(String name){
        if (name.isEmpty() || isStringContainNumber(name)){
            return false;
        }
        else{
            return true;
        }
    }

    private Boolean isStringContainNumber(String string){
        Pattern P = Pattern.compile("[0-9]");
        Matcher m = P.matcher(string);
        return m.find();
    }


    private TextWatcher TWNamDep = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher TWNamBel = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
