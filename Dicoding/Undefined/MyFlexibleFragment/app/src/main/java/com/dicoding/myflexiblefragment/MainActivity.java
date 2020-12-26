package com.dicoding.myflexiblefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)){
            Log.d("MyFlexibleFragmen", "Fragment Name : "+HomeFragment.class.getSimpleName());
            fragmentManager.beginTransaction().add(R.id.frame_container, homeFragment, HomeFragment.class.getSimpleName()).commit();
        }
    }
}
