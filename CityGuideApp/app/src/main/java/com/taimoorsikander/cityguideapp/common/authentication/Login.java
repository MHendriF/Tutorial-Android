package com.taimoorsikander.cityguideapp.common.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.taimoorsikander.cityguideapp.R;
import com.taimoorsikander.cityguideapp.databases.CheckInternet;
import com.taimoorsikander.cityguideapp.databases.SessionManager;
import com.taimoorsikander.cityguideapp.locationOwner.RetailerDashboard;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    ImageView btnBack;
    TextInputLayout phoneNumber, password;
    CountryCodePicker countryCodePicker;
    Button btnLogin, btnSignUp;
    RelativeLayout progressbar;
    CheckBox rememberMe;
    TextInputEditText edtPhoneNumber, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);

        btnBack = findViewById(R.id.iv_back_button);
        countryCodePicker = findViewById(R.id.login_country_code_picker);
        phoneNumber = findViewById(R.id.login_phone_number);
        password = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_sign_up);
        progressbar = findViewById(R.id.progressbar);
        rememberMe = findViewById(R.id.cb_remember_me);
        edtPhoneNumber = findViewById(R.id.edt_phone_number);
        edtPassword = findViewById(R.id.edt_password);

        SessionManager sessionManager = new SessionManager(Login.this, SessionManager.SESSION_REMEMBER_ME);
        if (sessionManager.checkRememberMe()){
            HashMap<String, String> rememberMeDetails = sessionManager.getRememberMeFromSession();
            edtPhoneNumber.setText(rememberMeDetails.get(SessionManager.KEY_SESSION_PHONE_NUMBER));
            edtPassword.setText(rememberMeDetails.get(SessionManager.KEY_SESSION_PASSWORD));
        }

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

    public void letTheUserLoggedIn(View view) {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            checkInternet.showCustomDialog(this);
        } else {
            if (!validateFields()) {
                return;
            }
            progressbar.setVisibility(View.VISIBLE);

            //get data
            String _getPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
            final String _password = password.getEditText().getText().toString().trim();

            if (_getPhoneNumber.charAt(0) == '0') {
                _getPhoneNumber = _getPhoneNumber.substring(1);
            }
            final String _phoneNumber = "+" + countryCodePicker.getFullNumber() + _getPhoneNumber;

            if (rememberMe.isChecked()){
                SessionManager sessionManager = new SessionManager(Login.this, SessionManager.SESSION_REMEMBER_ME);
                sessionManager.createRememberMeSession(_getPhoneNumber, _password);
            }

            //database
            Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo").equalTo(_phoneNumber);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        phoneNumber.setError(null);
                        phoneNumber.setErrorEnabled(false);

                        String systemPassword = dataSnapshot.child(_phoneNumber).child("password").getValue(String.class);
                        if (systemPassword.equals(_password)) {
                            password.setError(null);
                            password.setErrorEnabled(false);

                            String _fullName = dataSnapshot.child(_phoneNumber).child("fullName").getValue(String.class);
                            String _email = dataSnapshot.child(_phoneNumber).child("email").getValue(String.class);
                            String _password = dataSnapshot.child(_phoneNumber).child("password").getValue(String.class);
                            String _phoneNo = dataSnapshot.child(_phoneNumber).child("phoneNo").getValue(String.class);
                            String _date = dataSnapshot.child(_phoneNumber).child("date").getValue(String.class);
                            String _username = dataSnapshot.child(_phoneNumber).child("username").getValue(String.class);
                            String _gender = dataSnapshot.child(_phoneNumber).child("gender").getValue(String.class);

                            SessionManager sessionManager = new SessionManager(Login.this, SessionManager.SESSION_USER);
                            sessionManager.createLoginSession(_fullName, _username, _email, _password, _date, _gender, _phoneNo);

                            startActivity(new Intent(getApplicationContext(), RetailerDashboard.class));

                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(Login.this, _fullName + "\n" + _email + "\n" + _phoneNo + "\n" + _date, Toast.LENGTH_SHORT).show();

                        } else {
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "No such user exist!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validateFields() {
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        if (_phoneNumber.isEmpty()) {
            phoneNumber.setError("Phone number can not be empty");
            phoneNumber.requestFocus();
            return false;
        } else if (_password.isEmpty()) {
            password.setError("Password can not be empty");
            password.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
