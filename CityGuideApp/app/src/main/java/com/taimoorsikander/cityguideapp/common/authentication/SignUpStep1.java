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

import com.google.android.material.textfield.TextInputLayout;
import com.taimoorsikander.cityguideapp.R;

public class SignUpStep1 extends AppCompatActivity {

    ImageView btnBack;
    TextView tvTitle;
    Button btnNext, btnLogin;
    TextInputLayout fullName, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up_step1);

        btnBack = findViewById(R.id.iv_back_button);
        tvTitle = findViewById(R.id.tv_title);
        btnLogin = findViewById(R.id.btn_login);
        btnNext = findViewById(R.id.btn_next);

        fullName = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpStep1.super.onBackPressed();
            }
        });
    }

    public void callNextSignUpScreen(View view) {

        if (!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()) {
            return;
        }

        String _fullName = fullName.getEditText().getText().toString().trim();
        String _email = email.getEditText().getText().toString().trim();
        String _username = username.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        Intent intent = new Intent(SignUpStep1.this, SignUpStep2.class);
        intent.putExtra("fullName", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("username", _username);
        intent.putExtra("password", _password);

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(btnBack, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(btnNext, "transition_next_btn");
        pairs[2] = new Pair<View, String>(btnLogin, "transition_login_btn");
        pairs[3] = new Pair<View, String>(tvTitle, "transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUpStep1.this, pairs);
        startActivity(intent, options.toBundle());
    }

    public void callLoginScreen(View view) {
        startActivity(new Intent(this, Login.class));
    }

    private boolean validateFullName() {
        String val = fullName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullName.setError("Field can not be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = username.getEditText().getText().toString().trim();
        String checkSpace = "\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            username.setError("Username is to long!");
            return false;
        } else if (!val.matches(checkSpace)) {
            username.setError("No white space are allowed!");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9_-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
//                "(?=.*[0-9])" +         //at least 1 digit
//                "(?=.*[a-z])" +         //at least 1 lower case letter
//                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white space
                ".{4,}" +               //at least 4 character
                "$";


        if (val.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Password should contain 4 characters!");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}
