package com.magdev.presentation.main.home

import android.content.Context
import android.location.Location
import com.magdev.R
import com.magdev.data.network.IWeatherApi
import com.magdev.domain.weather.WeatherResponse
import com.magdev.presentation.common.injection.DomainModule
import com.magdev.presentation.common.injection.RxScheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class HomeInteractor @Inject constructor(
    @Named(DomainModule.ioToUI) private val rxScheduler: RxScheduler,
    private val context: Context,
    private val weatherApi: IWeatherApi) {

    fun getWeather(): Single<WeatherResponse> {
        return weatherApi.getWeather(
            lat = 56.5,
            lon = 84.9667,
            appid = context.getString(R.string.open_weather_app_id)
        ).compose(rxScheduler.getSingleTransformer())
    }
}
