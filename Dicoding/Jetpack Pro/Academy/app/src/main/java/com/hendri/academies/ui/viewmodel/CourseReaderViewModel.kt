package com.hendri.academies.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.hendri.academies.data.source.local.entity.ModuleEntity
import com.hendri.academies.data.AcademyRepository

class CourseReaderViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getSelectedModule(): ModuleEntity = academyRepository.getContent(courseId, moduleId)

    fun getModules(): List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}