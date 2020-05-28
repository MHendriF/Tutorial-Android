package com.taimoorsikander.cityguideapp.common.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.taimoorsikander.cityguideapp.R;
import com.taimoorsikander.cityguideapp.user.UserDashboard;

public class Login extends AppCompatActivity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);

        btnBack = findViewById(R.id.iv_back_button);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login.super.onBackPressed();
            }
        });
    }

    public void callForgetPasswordScreen(View view) {
        startActivity(new Intent(this, ForgetPassword.class));
    }

    public void callSingUpScreen(View view) {
        startActivity(new Intent(this, SignUpStep1.class));
    }

    public void callDashboardScreen(View view) {
        startActivity(new Intent(this, UserDashboard.class));
    }
}
