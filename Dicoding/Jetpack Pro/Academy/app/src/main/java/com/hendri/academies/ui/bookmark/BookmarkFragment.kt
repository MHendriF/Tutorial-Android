package com.hendri.academies.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hendri.academies.R
import com.hendri.academies.data.source.local.entity.CourseEntity
import com.hendri.academies.databinding.FragmentBookmarkBinding
import com.hendri.academies.ui.adapter.BookmarkAdapter
import com.hendri.academies.ui.callback.BookmarkFragmentCallback
import com.hendri.academies.ui.viewmodel.BookmarkViewModel
import com.hendri.academies.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_bookmark.*


class BookmarkFragment : Fragment(), BookmarkFragmentCallback {

    private var _fragmentBookmarkBinding: FragmentBookmarkBinding? = null
    private val binding get() = _fragmentBookmarkBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _fragmentBookmarkBinding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]

            val adapter = BookmarkAdapter(this)
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getBookmarks().observe(viewLifecycleOwner, { courses ->
                binding?.progressBar?.visibility = View.GONE
                adapter.setCourses(courses)
                adapter.notifyDataSetChanged()
            })

            binding?.rvBookmark?.layoutManager = LinearLayoutManager(context)
            binding?.rvBookmark?.setHasFixedSize(true)
            binding?.rvBookmark?.adapter = adapter
        }
    }
    override fun onShareClick(course: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).apply {
                setType(mimeType)
                setChooserTitle("Bagikan aplikasi ini sekarang.")
                setText(resources.getString(R.string.share_text, course.title))
                startChooser()
            }
        }
    }
}