package com.taimoorsikander.cityguideapp.common.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.taimoorsikander.cityguideapp.R;

public class SignUp extends AppCompatActivity {

    ImageView btnBack;
    TextView tvTitle;
    Button btnNext, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up);

        btnBack = findViewById(R.id.iv_back_button);
        tvTitle = findViewById(R.id.tv_title);
        btnLogin = findViewById(R.id.btn_login);
        btnNext = findViewById(R.id.btn_next);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp.super.onBackPressed();
            }
        });
    }

    public void callNextSignUpScreen(View view) {
        Intent intent = new Intent(SignUp.this, SignUp2nd.class);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(btnBack, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(btnNext, "transition_next_btn");
        pairs[2] = new Pair<View, String>(btnLogin, "transition_login_btn");
        pairs[3] = new Pair<View, String>(tvTitle, "transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
        startActivity(intent, options.toBundle());
    }

    public void callLoginScreen(View view) {
        startActivity(new Intent(this, Login.class));
    }

}
