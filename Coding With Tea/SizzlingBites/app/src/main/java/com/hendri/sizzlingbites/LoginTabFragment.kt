package com.hendri.sizzlingbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_tab_fragment.view.*
import timber.log.Timber

class LoginTabFragment: Fragment() {
    private var v = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.email.alpha = v
        view.password.alpha = v
        view.forgotPassword.alpha = v
        view.login.alpha = v

        view.email.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(300).start()
        view.password.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
        view.forgotPassword.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
        view.login.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(700).start()
    }
}