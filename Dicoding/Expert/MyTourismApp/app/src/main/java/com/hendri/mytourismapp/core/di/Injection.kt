package com.hendri.mytourismapp.core.di

import android.content.Context

import com.hendri.mytourismapp.core.data.source.local.LocalDataSource
import com.dicoding.tourismapp.core.data.source.local.room.TourismDatabase

import com.hendri.mytourismapp.core.data.TourismRepository
import com.hendri.mytourismapp.core.data.source.remote.RemoteDataSource
import com.hendri.mytourismapp.core.utils.AppExecutors
import com.hendri.mytourismapp.core.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): TourismRepository {
        val database = TourismDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutors()

        return TourismRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
