package com.magdev.data.network

import com.magdev.domain.weather.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApi {

    @GET("data/2.5/onecall")
    fun getWeather(@Query("lat") lat: Double,
                   @Query("lon") lon: Double,
                   @Query("units") units: String? = "metric",
                   @Query("exclude") exclude: String? = "hourly,minutely",
                   @Query("lang") lang: String? = "ru",
                   @Query("appid") appid: String?): Single<WeatherResponse>

}
