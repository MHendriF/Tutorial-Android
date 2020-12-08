package com.hendri.academies.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hendri.academies.R
import com.hendri.academies.data.source.local.entity.ModuleEntity
import com.hendri.academies.databinding.FragmentModuleListBinding
import com.hendri.academies.ui.adapter.ModuleListAdapter
import com.hendri.academies.ui.adapter.MyAdapterClickListener
import com.hendri.academies.ui.callback.CourseReaderCallback
import com.hendri.academies.ui.reader.CourseReaderActivity
import com.hendri.academies.ui.viewmodel.CourseReaderViewModel
import com.hendri.academies.viewmodel.ViewModelFactory
import com.hendri.academies.vo.Status
import kotlinx.android.synthetic.main.fragment_module_list.*


class ModuleListFragment : Fragment(), MyAdapterClickListener {

    companion object {
        val TAG = ModuleListFragment::class.java.simpleName
        fun newInstance(): ModuleListFragment = ModuleListFragment()
    }

    private var _fragmentModuleListBinding: FragmentModuleListBinding? = null
    private val binding get() = _fragmentModuleListBinding

    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback
    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _fragmentModuleListBinding = FragmentModuleListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
        adapter = ModuleListAdapter(this)

        progress_bar.visibility = View.VISIBLE
        viewModel.modules.observe(viewLifecycleOwner, { moduleEntities ->
            if (moduleEntities != null) {
                when (moduleEntities.status) {
                    Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding?.progressBar?.visibility = View.GONE
                        populateRecyclerView(moduleEntities.data as List<ModuleEntity>)
                    }
                    Status.ERROR -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
        viewModel.setSelectedModule(moduleId)
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        binding?.progressBar?.visibility = View.GONE
        adapter.setModules(modules)
        binding?.rvModule?.layoutManager = LinearLayoutManager(context)
        binding?.rvModule?.setHasFixedSize(true)
        binding?.rvModule?.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(binding?.rvModule?.context, DividerItemDecoration.VERTICAL)
        binding?.rvModule?.addItemDecoration(dividerItemDecoration)
    }
}