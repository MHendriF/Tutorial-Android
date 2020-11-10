package com.hendri.academies.ui.callback

import com.hendri.academies.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
