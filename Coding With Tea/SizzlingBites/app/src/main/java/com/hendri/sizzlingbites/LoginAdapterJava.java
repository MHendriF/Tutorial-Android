package com.hendri.sizzlingbites;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapterJava extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public LoginAdapterJava(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
           case 1:
           default:return null;
       }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
