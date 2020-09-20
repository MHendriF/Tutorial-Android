package com.taimoorsikander.cityguideapp.common.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.taimoorsikander.cityguideapp.R;
import com.taimoorsikander.cityguideapp.databases.CheckInternet;

public class ForgetPassword extends AppCompatActivity {

    ImageView btnBack;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;
    RelativeLayout progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forget_password);

        btnBack = findViewById(R.id.iv_back_button);
        countryCodePicker = findViewById(R.id.forgot_country_code_picker);
        phoneNumber = findViewById(R.id.forgot_phone_number);
        progressbar = findViewById(R.id.progressbar);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetPassword.super.onBackPressed();
            }
        });
    }

    public void verifyPhoneNumber(View view) {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            checkInternet.showCustomDialog(this);
        } else {
            if (!validateFields()) {
                return;
            }
            progressbar.setVisibility(View.VISIBLE);

            String _getPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
            if (_getPhoneNumber.charAt(0) == '0') {
                _getPhoneNumber = _getPhoneNumber.substring(1);
            }
            final String _phoneNumber = "+" + countryCodePicker.getFullNumber() + _getPhoneNumber;

            //database
            Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo").equalTo(_phoneNumber);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        phoneNumber.setError(null);
                        phoneNumber.setErrorEnabled(false);

                        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);
                        intent.putExtra("phoneNumber", _phoneNumber);
                        intent.putExtra("whatToDo", "updateData");
                        startActivity(intent);
                        finish();

                    } else {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(ForgetPassword.this, "No such user exist!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(ForgetPassword.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validateFields() {
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        if (_phoneNumber.isEmpty()) {
            phoneNumber.setError("Phone number can not be empty");
            phoneNumber.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
