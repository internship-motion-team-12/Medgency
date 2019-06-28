package com.example.medgency;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medgency.model.User;

public class SignUpEmail extends AppCompatActivity {
    private TextView TV1,TV2;
    private EditText ETPassword, ETEmail;
    private Button mButton;
    private ImageView PasswordToggle, LineToVisibleGone;
    private User user = new User ("Belum di assign","Belum di assign","Belum di assign");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);

        Intent intent = getIntent();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.ToolbarSignUpEmail);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user.setNamaDepan(intent.getStringExtra(getString(R.string.NamaDepan)));
        user.setNamaBelakang(intent.getStringExtra(getString(R.string.NamaBelakang)));
        user.setJenisKelamin(intent.getStringExtra(getString(R.string.JenisKelamin)));

        TV1 = findViewById(R.id.TVDaftarSignUpEmail);
        TV2 = findViewById(R.id.TVMasukanEmailDanPassword);
        mButton = findViewById(R.id.ButtonSignUpEmail);
        LineToVisibleGone = findViewById(R.id.LineToVisibleGone);
        PasswordToggle = findViewById(R.id.PasswordToggle);
        ETPassword = findViewById(R.id.ETPassword);
        ETEmail = findViewById(R.id.ETEmail);

        Typeface mTF = Typeface.createFromAsset(getAssets(),"font/NunitoSans-Regular.ttf");

        TV1.setTypeface(mTF);
        TV2.setTypeface(mTF);
        mButton.setTypeface(mTF);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RunOnBoardActivity(user);
            }
        });

        PasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LineToVisibleGone.getVisibility() == View.VISIBLE){
                    //kalau togglenya terlihat, maka toggle di nonaktifkan dan visibilty passwordnya terlihat
                    LineToVisibleGone.setVisibility(View.GONE);
                    ETPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ETPassword.setSelection(ETPassword.length());

                }
                else{
                    // kalau togglenya tidak terlihat, maka toggle di aktifkan dan visibility passwordnya tidak terlihat
                    LineToVisibleGone.setVisibility(View.VISIBLE);
                    ETPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ETPassword.setSelection(ETPassword.length());
                }
            }
        });
    }

    private void RunOnBoardActivity(User user) {
        Intent i = new Intent(this, HomeActivity.class);
        user.setEmail(ETEmail.getText().toString());
        user.setPassword(ETPassword.getText().toString());
        i.putExtra(getString(R.string.NamaDepan),user.getNamaDepan());
        i.putExtra(getString(R.string.NamaBelakang),user.getNamaBelakang());
        i.putExtra(getString(R.string.email),user.getEmail());
        i.putExtra(getString(R.string.password),user.getPassword());
        i.putExtra(getString(R.string.JenisKelamin),user.getJenisKelamin());
        i.putExtra("Status","Destroy");
        startActivity(i);
        finish();
    }
}
