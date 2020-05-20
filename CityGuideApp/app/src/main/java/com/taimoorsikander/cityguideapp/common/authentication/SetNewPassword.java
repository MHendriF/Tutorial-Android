package com.taimoorsikander.cityguideapp.common.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.taimoorsikander.cityguideapp.R;

public class SetNewPassword extends AppCompatActivity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_set_new_password);

        btnBack = findViewById(R.id.iv_back_button);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetNewPassword.super.onBackPressed();
            }
        });
    }

    public void callMessageSuccessScreen(View view) {
        startActivity(new Intent(this, ForgetPasswordSuccessMessage.class));
    }
}
