package com.hendri.academies.ui.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hendri.academies.R
import com.hendri.academies.databinding.FragmentAcademyBinding
import com.hendri.academies.ui.adapter.AcademyAdapter
import com.hendri.academies.ui.viewmodel.AcademyViewModel
import com.hendri.academies.viewmodel.ViewModelFactory
import com.hendri.academies.vo.Status
import kotlinx.android.synthetic.main.fragment_academy.*


class AcademyFragment : Fragment() {

    private var _fragmentAcademyBinding: FragmentAcademyBinding? = null
    private val binding get() = _fragmentAcademyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentAcademyBinding = FragmentAcademyBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[AcademyViewModel::class.java]

            val academyAdapter = AcademyAdapter()
            progress_bar.visibility = View.VISIBLE
            viewModel.getCourses().observe(viewLifecycleOwner, { courses ->
                if (courses != null) {
                    when (courses.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding?.progressBar?.visibility = View.GONE
                            academyAdapter.setCourses(courses.data)
                            academyAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding?.rvAcademy) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = academyAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentAcademyBinding = null
    }
}