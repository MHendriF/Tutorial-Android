package com.hendri.academies.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hendri.academies.di.Injection
import com.hendri.academies.data.AcademyRepository
import com.hendri.academies.ui.viewmodel.AcademyViewModel
import com.hendri.academies.ui.viewmodel.BookmarkViewModel
import com.hendri.academies.ui.viewmodel.CourseReaderViewModel
import com.hendri.academies.ui.viewmodel.DetailCourseViewModel

class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }
    }

    @SuppressWarnings("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> {
                return AcademyViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> {
                return DetailCourseViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                return BookmarkViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
                return CourseReaderViewModel(mAcademyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}