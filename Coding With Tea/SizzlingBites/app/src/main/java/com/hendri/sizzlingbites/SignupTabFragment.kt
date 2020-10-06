package com.hendri.sizzlingbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.signup_tab_fragment.view.*
import timber.log.Timber

class SignupTabFragment: Fragment() {

    private val v = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signup_tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("masuk")

        view.email.alpha = v
        view.phone.alpha = v
        view.password.alpha = v
        view.c_password.alpha = v
        view.signup.alpha = v

        view.email.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(300).start()
        view.phone.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(400).start()
        view.password.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
        view.c_password.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(600).start()
        view.signup.animate().translationX(0f).alpha(1f).setDuration(800).setStartDelay(700).start()
    }
}