package id.skillacademy.mvparchitecture.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.skillacademy.mvparchitecture.di.builder.module.HomeModule
import id.skillacademy.mvparchitecture.presentation.HomeActivity
import id.skillacademy.mvparchitecture.di.builder.scope.Presentation

@Module
abstract class ActivityBuilder {

    @Presentation
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}