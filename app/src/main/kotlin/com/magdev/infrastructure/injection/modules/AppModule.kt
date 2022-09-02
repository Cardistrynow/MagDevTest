package com.magdev.infrastructure.injection.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.rxrelay2.PublishRelay
import dagger.Module
import dagger.Provides
import com.magdev.infrastructure.injection.EventBus
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun providesPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName ,Context.MODE_PRIVATE)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideBus(): EventBus {
        return PublishRelay.create()
    }
}
