package com.hendri.academies.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hendri.academies.R
import com.hendri.academies.databinding.ActivityHomeBinding
import com.hendri.academies.ui.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var _activityHomeBinding: ActivityHomeBinding? = null
    private val binding get() = _activityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding?.viewPager?.adapter = sectionsPagerAdapter
        binding?.tabs?.setupWithViewPager(binding?.viewPager)

        supportActionBar?.elevation = 0f
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityHomeBinding = null
    }
}