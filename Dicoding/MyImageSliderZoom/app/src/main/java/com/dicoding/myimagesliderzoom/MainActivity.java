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
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ViewPager slider;
    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slider = findViewById(R.id.slider);
        sliderAdapter = new SliderAdapter(this);
        slider.setAdapter(sliderAdapter);

    }

    private class SliderAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;

        int[] sampleImages = {
                R.drawable.rumah_1,
                R.drawable.rumah_2,
                R.drawable.rumah_3,
                R.drawable.rumah_4,
                R.drawable.rumah_5
        };

        public SliderAdapter(Context context) {
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
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_slider, container, false);
            ImageView imageView = view.findViewById(R.id.img_item);
            imageView.setImageResource(sampleImages[position]);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FullscreenActivity.class);
                    intent.putExtra("posisi", position);
                    startActivity(intent);
                }
            });

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ImageView) object);
        }
    }
}
