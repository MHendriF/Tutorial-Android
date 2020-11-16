package com.hendri.myidlingresource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            delay1()
        }
    }

    private fun delay1() {
        Handler().postDelayed({
            text_view.text = getString(R.string.delay1)
        }, 2000)
    }
}