package com.hendri.videomeeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.ivBack).setOnClickListener(view -> onBackPressed());
        findViewById(R.id.tvSignIn).setOnClickListener(view -> onBackPressed());
    }
}