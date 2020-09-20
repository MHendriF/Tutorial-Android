package com.hendri.sizzlingbites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        lottie_animation.animate().translationY(2000.toFloat()).setDuration(1000).startDelay = 4000
        iv_logo.animate().translationY(2000.toFloat()).setDuration(1000).startDelay = 4000
        iv_app_name.animate().translationY(2000.toFloat()).setDuration(1000).startDelay = 4000
        iv_bg.animate().translationY(2000.toFloat()).setDuration(1000).startDelay = 4000
    }
}