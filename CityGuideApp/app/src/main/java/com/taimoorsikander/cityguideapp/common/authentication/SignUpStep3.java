package com.taimoorsikander.cityguideapp.common.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.taimoorsikander.cityguideapp.R;

public class SignUpStep3 extends AppCompatActivity {

    ImageView btnBack;
    TextView tvTitle;
    Button btnNext, btnLogin;
    TextInputLayout phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up3third);

        btnBack = findViewById(R.id.iv_back_button);
        tvTitle = findViewById(R.id.tv_title);
        btnLogin = findViewById(R.id.btn_login);
        btnNext = findViewById(R.id.btn_next);
        phone = findViewById(R.id.signup_phone);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpStep3.super.onBackPressed();
            }
        });

    }

    public void callMakeSelectionScreen(View view) {
        if (!validatePhoneNumber()) {
            return;
        }
        startActivity(new Intent(this, MakeSelection.class));
    }

    private boolean validatePhoneNumber(){
        String val = phone.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            phone.setError("Field can not be empty");
            return false;
        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }

}
