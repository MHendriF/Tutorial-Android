package id.skillacademy.mvparchitecture

import dagger.android.AndroidInjection.inject
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import id.skillacademy.mvparchitecture.di.builder.component.DaggerApplicationComponent

class BelajarApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerApplicationComponent.builder().build()
        return DaggerApplicationComponent.create().apply { inject(this@BelajarApp) }
    }

}