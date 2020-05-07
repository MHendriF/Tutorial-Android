package com.dicoding.myimagesliderzoom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

public class FullscreenActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FullscreenAdapter fullscreenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        viewPager = findViewById(R.id.full_slider);
        fullscreenAdapter = new FullscreenAdapter(this);
        viewPager.setAdapter(fullscreenAdapter);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("posisi", 0);
        viewPager.setCurrentItem(pos);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){

        }
    }

    private class FullscreenAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;

        int[] sampleImages = {
                R.drawable.rumah_1,
                R.drawable.rumah_2,
                R.drawable.rumah_3,
                R.drawable.rumah_4,
                R.drawable.rumah_5
        };

        public FullscreenAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return sampleImages.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return (view == object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_fullscreen, container, false);
            PhotoView photoView = view.findViewById(R.id.fullscreen_img);
            photoView.setImageResource(sampleImages[position]);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((PhotoView) object);
        }
    }
}
