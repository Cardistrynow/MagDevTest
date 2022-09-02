package com.magdev.infrastructure.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import com.magdev.infrastructure.App
import com.magdev.infrastructure.injection.modules.*
import com.magdev.presentation.common.injection.DomainModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBinderModule::class,
    DomainModule::class,
    AppModule::class,
    RepositoryModule::class,
    NetworkModule::class,
    ApiModule::class,
    NavigationModule::class
])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
