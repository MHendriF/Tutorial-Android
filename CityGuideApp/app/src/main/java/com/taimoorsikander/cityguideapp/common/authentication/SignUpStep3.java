package com.taimoorsikander.cityguideapp.common.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.taimoorsikander.cityguideapp.R;

public class SignUpStep3 extends AppCompatActivity {

    ScrollView scrollView;
    ImageView btnBack;
    TextView tvTitle;
    Button btnNext, btnLogin;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up_step3);

        btnBack = findViewById(R.id.iv_back_button);
        tvTitle = findViewById(R.id.tv_title);
        btnLogin = findViewById(R.id.btn_login);
        btnNext = findViewById(R.id.btn_next);
        scrollView = findViewById(R.id.signup_step3_scrollview);
        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumber = findViewById(R.id.signup_phone_number);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpStep3.super.onBackPressed();
            }
        });

    }

    public void callVerifyOTPScreen(View view) {
        if (!validatePhoneNumber()) {
            return;
        }

        String _fullName = getIntent().getStringExtra("fullName");
        String _email = getIntent().getStringExtra("email");
        String _username = getIntent().getStringExtra("username");
        String _password = getIntent().getStringExtra("password");
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");

        String _getPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _phoneNumber = "+"+countryCodePicker.getFullNumber()+_getPhoneNumber;

        Log.d("Trace", "_getPhoneNumber: "+_getPhoneNumber);
        Log.d("Trace", "_phoneNumber: "+_phoneNumber);

        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);
        intent.putExtra("fullName", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("username", _username);
        intent.putExtra("password", _password);
        intent.putExtra("date", _date);
        intent.putExtra("gender", _gender);
        intent.putExtra("phoneNumber", _phoneNumber);

        //Add Transition
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(scrollView, "transition_OTP_screen");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpStep3.this, pairs);
        startActivity(intent, options.toBundle());

    }

    private boolean validatePhoneNumber(){
        String val = phoneNumber.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            phoneNumber.setError("Field can not be empty");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }

}
