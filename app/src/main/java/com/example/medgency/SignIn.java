package com.example.medgency;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.medgency.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {
    private TextView TVLogin, TV1, TV2;
    private Button mButton;
    private EditText ETpassword,ETEmail;
    private ImageView PasswordToggle, LineToVisibleGone;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.ToolbarLogin);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TVLogin = findViewById(R.id.TVLogin);
        TV1 = findViewById(R.id.TVBelumPunyaAkun);
        TV2 = findViewById(R.id.TVDaftarSekarang);
        PasswordToggle = findViewById(R.id.PasswordToggle);
        ETEmail = findViewById(R.id.ETEmailLogin);
        ETpassword = findViewById(R.id.ETPasswordLogin);
        mButton = findViewById(R.id.ButtonLogin);
        LineToVisibleGone = findViewById(R.id.LineToVisibleGone);

        LineToVisibleGone.setVisibility(View.VISIBLE);
        ETpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); //to hide
        ETpassword.setSelection(ETpassword.length());

        Typeface mTF = Typeface.createFromAsset(getAssets(),"font/NunitoSans-Regular.ttf");

        TV1.setTypeface(mTF);
        TV2.setTypeface(mTF);
        TVLogin.setTypeface(mTF);
        mButton.setTypeface(mTF);

        PasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LineToVisibleGone.getVisibility() == View.VISIBLE){
                    //kalau togglenya terlihat, maka toggle di nonaktifkan dan visibilty passwordnya terlihat
                    LineToVisibleGone.setVisibility(View.GONE);
                    ETpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ETpassword.setSelection(ETpassword.length());

                }
                else{
                    // kalau togglenya tidak terlihat, maka toggle di aktifkan dan visibility passwordnya tidak terlihat
                    LineToVisibleGone.setVisibility(View.VISIBLE);
                    ETpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    ETpassword.setSelection(ETpassword.length());
                }
            }
        });

        TV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RunActivitySignUp(user);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RunMainActivity(user);
            }
        });
    }

    private void RunActivitySignUp(User user) {
        Intent i = new Intent(this,SignUp.class);
        startActivity(i);
        finish();
    }

    private void MakeWarningToast() {
        // coming soon
    }

    private void RunMainActivity(User user) {
        if (isValidEmail(ETEmail.getText().toString()) && isValidPassword(ETpassword.getText().toString())){
            Intent i = new Intent(this, HomeActivity.class);
            /*
            i.putExtra(getString(R.string.NamaDepan),user.getNamaDepan());
            i.putExtra(getString(R.string.NamaBelakang),user.getNamaBelakang());
            i.putExtra(getString(R.string.email),user.getEmail());
            i.putExtra(getString(R.string.password),user.getPassword());
            i.putExtra(getString(R.string.JenisKelamin),user.getJenisKelamin());
            */
            startActivity(i);
            finish();
        }
        else{
            MakeWarningToast();
        }
    }

    private Boolean isValidPassword(String password){
        if (password.isEmpty() || password.length() <= 7 || !isStringContainNumber(password)){
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private Boolean isStringContainNumber(String string){
        Pattern P = Pattern.compile("[0-9]");
        Matcher m = P.matcher(string);
        return m.find();
    }
}
