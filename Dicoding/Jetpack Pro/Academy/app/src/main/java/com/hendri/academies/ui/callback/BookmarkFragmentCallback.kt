package com.hendri.academies.ui.callback

import com.hendri.academies.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
