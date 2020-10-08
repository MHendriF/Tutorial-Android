package com.hendri.sizzlingbites.java;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hendri.sizzlingbites.OnBoardingFragment1;
import com.hendri.sizzlingbites.OnBoardingFragment2;
import com.hendri.sizzlingbites.OnBoardingFragment3;
import com.hendri.sizzlingbites.R;

public class IntroJavaActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.liquid_pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
           switch (position) {
               case 0:
                   OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                   return tab1;
               case 1:
                   OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                   return tab2;
               case 2:
                   OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                   return tab3;
           }
           return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
