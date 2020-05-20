package com.taimoorsikander.cityguideapp.common.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.taimoorsikander.cityguideapp.R;

public class MakeSelection extends AppCompatActivity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_make_selection);

        btnBack = findViewById(R.id.iv_back_button);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeSelection.super.onBackPressed();
            }
        });

    }

    public void callVerifyOTPScreen(View view) {
        startActivity(new Intent(this, VerifyOTP.class));
    }
}
