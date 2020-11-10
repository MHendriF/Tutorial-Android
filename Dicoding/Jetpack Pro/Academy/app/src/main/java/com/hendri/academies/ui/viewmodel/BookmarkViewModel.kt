package com.hendri.academies.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.hendri.academies.data.source.local.entity.CourseEntity
import com.hendri.academies.data.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    fun getBookmarks(): List<CourseEntity> = academyRepository.getBookmarkedCourses()
}