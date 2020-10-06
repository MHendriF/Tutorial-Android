package com.hendri.sizzlingbites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    private var v = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Timber.d("masuk")

        tab_layout.addTab(tab_layout.newTab().setText("Login"))
        tab_layout.addTab(tab_layout.newTab().setText("Signup"))
        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        val loginAdapter = LoginAdapter(supportFragmentManager, this, tab_layout.tabCount)
        view_pager.adapter = loginAdapter

        view_pager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tab_layout))

        fab_fb.translationY = 300f
        fab_google.translationY = 300f
        fab_twitter.translationY = 300f
        tab_layout.translationY = 300f

        fab_fb.alpha = v
        fab_google.alpha = v
        fab_twitter.alpha = v
        tab_layout.alpha = v

        fab_fb.animate().translationY(0f).alpha(1f).setDuration(1000).setStartDelay(400).start()
        fab_google.animate().translationY(0f).alpha(1f).setDuration(1000).setStartDelay(600).start()
        fab_twitter.animate().translationY(0f).alpha(1f).setDuration(1000).setStartDelay(800).start()
        tab_layout.animate().translationY(0f).alpha(1f).setDuration(1000).setStartDelay(1000).start()
    }
}