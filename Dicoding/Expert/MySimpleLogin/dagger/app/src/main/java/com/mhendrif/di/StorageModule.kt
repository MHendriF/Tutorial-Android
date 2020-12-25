package com.mhendrif.di

import android.content.Context
import com.mhendrif.mysimplelogin.SessionManager
import dagger.Module
import dagger.Provides

@Module
class StorageModule {
    @Provides
    fun provideSessionManager(context: Context): SessionManager = SessionManager(context)
}