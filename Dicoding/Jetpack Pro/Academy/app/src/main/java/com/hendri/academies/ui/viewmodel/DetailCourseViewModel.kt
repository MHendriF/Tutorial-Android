package com.hendri.academies.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.hendri.academies.data.source.local.entity.CourseEntity
import com.hendri.academies.data.source.local.entity.ModuleEntity
import com.hendri.academies.data.AcademyRepository

class DetailCourseViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModules(): List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}