package com.magdev.infrastructure.injection.modules

import dagger.Module
import dagger.Provides
import com.magdev.data.network.IWeatherApi
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): IWeatherApi {
        return retrofit.create(IWeatherApi::class.java)
    }
}
