package com.taimoorsikander.cityguideapp.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taimoorsikander.cityguideapp.HelperClasses.SliderAdapter;
import com.taimoorsikander.cityguideapp.R;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout llDots;
    SliderAdapter sliderAdapter;
    TextView[] tvDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.slider);
        llDots = findViewById(R.id.ll_dots);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    private void addDots(int position){
        tvDots = new TextView[4];
        llDots.removeAllViews();

        for (int i=0; i<tvDots.length; i++){
            tvDots[i] = new TextView(this);
            tvDots[i].setText(Html.fromHtml("&#8226;"));
            tvDots[i].setTextSize(35);

            llDots.addView(tvDots[i]);
        }
        if (tvDots.length > 0){
            tvDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
