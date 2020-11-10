package com.hendri.academies.di

import android.content.Context
import com.hendri.academies.data.AcademyRepository
import com.hendri.academies.data.source.remote.RemoteDataSource
import com.hendri.academies.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)

    }
}