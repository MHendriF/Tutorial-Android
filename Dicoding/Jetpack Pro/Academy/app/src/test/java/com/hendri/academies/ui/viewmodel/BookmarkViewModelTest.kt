package com.hendri.academies.ui.viewmodel

import com.hendri.academies.data.AcademyRepository
import com.hendri.academies.data.source.local.entity.CourseEntity
import com.hendri.academies.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {
    private lateinit var viewModel: BookmarkViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmarks() {
        `when`<ArrayList<CourseEntity>>(academyRepository.getBookmarkedCourses()).thenReturn(
            ArrayList(DataDummy.generateDummyCourses())
        )
        val courseEntities = viewModel.getBookmarks()
        verify<AcademyRepository>(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}