package com.hendri.sizzlingbites

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    companion object {
        val NUM_PAGES = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        supportActionBar?.hide()

        val pagerAdapter = ScreenSlidePagerAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        liquid_pager.adapter = pagerAdapter
        liquid_pager.startAnimation(AnimationUtils.loadAnimation(this, R.anim.o_b_anim))

        lottie_animation.animate().translationY(2000.toFloat()).setDuration(1000).startDelay = 4000
        iv_logo.animate().translationY(2000.toFloat()).setDuration(1000).startDelay = 4000
        iv_app_name.animate().translationY(2000.toFloat()).setDuration(1000).startDelay = 4000
        iv_bg.animate().translationY(2000.toFloat()).setDuration(1000).startDelay = 4000
    }

     class ScreenSlidePagerAdapter(fm: FragmentManager, behavior: Int) :
         FragmentStatePagerAdapter(fm, behavior) {

         override fun getItem(position: Int): Fragment {
             return when (position) {
                 0 -> OnBoardingFragment1()
                 1 -> OnBoardingFragment2()
                 2 -> OnBoardingFragment3()
                 else -> OnBoardingFragment1()
             }
         }

        override fun getCount(): Int {
            return NUM_PAGES
        }
    }
}