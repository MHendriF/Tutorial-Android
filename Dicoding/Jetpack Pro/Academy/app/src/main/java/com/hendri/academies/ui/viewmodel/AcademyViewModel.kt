package com.hendri.academies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.hendri.academies.data.source.local.entity.CourseEntity
import com.hendri.academies.data.AcademyRepository
import com.hendri.academies.vo.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    fun getCourses(): LiveData<Resource<PagedList<CourseEntity>>> =
        academyRepository.getAllCourses()
}