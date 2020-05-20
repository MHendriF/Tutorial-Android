package id.skillacademy.mvparchitecture.di.builder.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import id.skillacademy.mvparchitecture.data.HomeDataSource
import id.skillacademy.mvparchitecture.presentation.HomeActivity
import id.skillacademy.mvparchitecture.presentation.HomePresenter
import id.skillacademy.mvparchitecture.presentation.HomeView
import retrofit2.Retrofit

@Module
abstract class HomeModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun providesHomeDataSource(retrofit: Retrofit): HomeDataSource =
            retrofit.create(HomeDataSource::class.java)

        @JvmStatic
        @Provides
        fun providesHomePresenter(view: HomeView,
                                  dataSource: HomeDataSource):
                HomePresenter = HomePresenter(view, dataSource)
    }

    @Binds
    abstract fun bindHomeView(activity: HomeActivity): HomeView
}