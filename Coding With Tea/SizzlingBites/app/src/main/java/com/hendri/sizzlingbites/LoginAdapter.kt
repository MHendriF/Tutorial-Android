package com.hendri.sizzlingbites

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class LoginAdapter(fm: FragmentManager, private val context: Context, var totalTabs: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0-> LoginTabFragment()
            1 -> SignupTabFragment()
            else -> LoginTabFragment()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}
