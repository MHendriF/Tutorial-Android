package com.hendri.academies.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hendri.academies.R
import com.hendri.academies.data.source.local.entity.ModuleEntity
import com.hendri.academies.databinding.FragmentModuleContentBinding
import com.hendri.academies.ui.viewmodel.CourseReaderViewModel
import com.hendri.academies.viewmodel.ViewModelFactory
import com.hendri.academies.vo.Status
import kotlinx.android.synthetic.main.fragment_module_content.*


class ModuleContentFragment : Fragment() {
    private lateinit var viewModel: CourseReaderViewModel

    private var _fragmentModuleContentBinding: FragmentModuleContentBinding? = null
    private val binding get() = _fragmentModuleContentBinding

    companion object {
        val TAG = ModuleContentFragment::class.java.simpleName

        fun newInstance(): ModuleContentFragment {
            return ModuleContentFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _fragmentModuleContentBinding = FragmentModuleContentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]

            progress_bar.visibility = View.VISIBLE
            viewModel.selectedModule.observe(viewLifecycleOwner, { moduleEntity ->
                if (moduleEntity != null) {
                    when (moduleEntity.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> if (moduleEntity.data != null) {
                            binding?.progressBar?.visibility = View.GONE
                            if (moduleEntity.data.contentEntity != null) {
                                populateWebView(moduleEntity.data)
                            }
                            setButtonNextPrevState(moduleEntity.data)
                            if (!moduleEntity.data.read) {
                                viewModel.readContent(moduleEntity.data)
                            }

                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }

                    binding?.btnNext?.setOnClickListener { viewModel.setNextPage() }
                    binding?.btnPrev?.setOnClickListener { viewModel.setPrevPage() }

                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentModuleContentBinding = null
    }

    private fun populateWebView(module: ModuleEntity) {
        module.contentEntity?.content?.let { web_view.loadData(it, "text/html", "UTF-8") }
    }

    private fun setButtonNextPrevState(module: ModuleEntity) {
        if (activity != null) {
            when (module.position) {
                0 -> {
                    binding?.btnPrev?.isEnabled = false
                    binding?.btnNext?.isEnabled = true
                }
                viewModel.getModuleSize() - 1 -> {
                    binding?.btnPrev?.isEnabled = true
                    binding?.btnNext?.isEnabled = false
                }
                else -> {
                    binding?.btnPrev?.isEnabled = true
                    binding?.btnNext?.isEnabled = true
                }
            }
        }
    }
}