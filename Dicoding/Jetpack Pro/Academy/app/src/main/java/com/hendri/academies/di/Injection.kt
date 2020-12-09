package com.hendri.academies.di

import android.content.Context
import com.hendri.academies.data.AcademyRepository
import com.hendri.academies.data.source.local.LocalDataSource
import com.hendri.academies.data.source.local.room.AcademyDatabase
import com.hendri.academies.data.source.remote.RemoteDataSource
import com.hendri.academies.utils.AppExecutors
import com.hendri.academies.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}