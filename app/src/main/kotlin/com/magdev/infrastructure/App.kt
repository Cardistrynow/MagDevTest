package com.magdev.infrastructure

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import com.magdev.infrastructure.injection.AppComponent
import com.magdev.infrastructure.injection.DaggerAppComponent
import javax.inject.Inject


class App : Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    private lateinit var cicerone: Cicerone<Router>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        initCicerone()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        appComponent.inject(this)
    }

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
