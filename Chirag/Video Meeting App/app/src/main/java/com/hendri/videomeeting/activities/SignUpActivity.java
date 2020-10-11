package com.hendri.videomeeting.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hendri.videomeeting.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.ivBack).setOnClickListener(view -> onBackPressed());
        findViewById(R.id.tvSignIn).setOnClickListener(view -> onBackPressed());
    }
}