package com.mhendrif.mysimplelogin

import android.app.Application
import com.mhendrif.di.AppComponent
import com.mhendrif.di.DaggerAppComponent

open class MyApplication : Application(){
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(
            applicationContext
        )
    }
}