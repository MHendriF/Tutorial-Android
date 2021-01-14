package com.mhendrif.myreportapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCrash = findViewById<Button>(R.id.btn_crash)
        btnCrash.setOnClickListener {
            FirebaseCrashlytics.getInstance().log("Clicked on button")
            FirebaseCrashlytics.getInstance().setCustomKey("str_key", "some_data")
            throw RuntimeException("Test Crash")
        }
    }
}