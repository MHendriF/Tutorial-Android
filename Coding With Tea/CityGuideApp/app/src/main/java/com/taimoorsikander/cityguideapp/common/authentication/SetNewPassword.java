package com.taimoorsikander.cityguideapp.common.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.taimoorsikander.cityguideapp.R;
import com.taimoorsikander.cityguideapp.databases.CheckInternet;

public class SetNewPassword extends AppCompatActivity {

    ImageView btnBack;
    TextInputLayout newPassword, confirmPassword;
    RelativeLayout progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_set_new_password);

        btnBack = findViewById(R.id.iv_back_button);
        newPassword = findViewById(R.id.set_new_password);
        confirmPassword = findViewById(R.id.set_confirm_password);
        progressbar = findViewById(R.id.progressbar);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetNewPassword.super.onBackPressed();
            }
        });
    }

    public void setNewPasswordBtn(View view) {
        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)) {
            checkInternet.showCustomDialog(this);
        } else {
            if (!validatePassword() | !validateConfirmPassword()){
                return;
            }
            progressbar.setVisibility(View.VISIBLE);

            String _newPassword = newPassword.getEditText().getText().toString().trim();
            String _phoneNumber =  getIntent().getStringExtra("phoneNo");

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.child(_phoneNumber).child("password").setValue(_newPassword);

            startActivity(new Intent(getApplicationContext(), ForgetPasswordSuccessMessage.class));
            finish();
        }
    }

    private boolean validatePassword() {
        String val = newPassword.getEditText().getText().toString().trim();
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
            newPassword.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            newPassword.setError("Password should contain 4 characters!");
            return false;
        } else {
            newPassword.setError(null);
            newPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String val = newPassword.getEditText().getText().toString().trim();
        String confirm = confirmPassword.getEditText().getText().toString().trim();
        if (!val.equals(confirm)){
            confirmPassword.setError("Password not matches!");
            return false;
        }  else {
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
            return true;
        }
    }
}
