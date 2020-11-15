package com.hendri.academies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hendri.academies.data.source.local.entity.CourseEntity
import com.hendri.academies.data.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    fun getBookmarks(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}