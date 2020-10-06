package com.hendri.sizzlingbites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_on_boarding3.view.*
import timber.log.Timber

class OnBoardingFragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_on_boarding3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("masuk")
        view.fab_next.setOnClickListener {
            Timber.d("next to login")
            val intent = Intent(activity, LoginActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}