package com.taimoorsikander.cityguideapp.locationOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.taimoorsikander.cityguideapp.R;
import com.taimoorsikander.cityguideapp.databases.SessionManager;

import java.util.HashMap;

public class RetailerDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_dashboard);

        TextView textView = findViewById(R.id.textview);

        SessionManager sessionManager = new SessionManager(this, SessionManager.SESSION_USER);
        HashMap<String, String> userDetails = sessionManager.getUserDetailFromSession();

        String _fullName = userDetails.get(SessionManager.KEY_FULLNAME);
        String _phoneNumber = userDetails.get(SessionManager.KEY_PHONE_NUMBER);

        textView.setText(_fullName+"\n"+_phoneNumber);
    }
}