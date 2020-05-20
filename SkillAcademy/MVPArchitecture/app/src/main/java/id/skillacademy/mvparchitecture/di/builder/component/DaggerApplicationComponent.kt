package id.skillacademy.mvparchitecture.di.builder.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import id.skillacademy.mvparchitecture.BelajarApp
import id.skillacademy.mvparchitecture.di.builder.ActivityBuilder
import id.skillacademy.mvparchitecture.di.builder.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    ActivityBuilder::class
])

interface DaggerApplicationComponent : AndroidInjector<BelajarApp>