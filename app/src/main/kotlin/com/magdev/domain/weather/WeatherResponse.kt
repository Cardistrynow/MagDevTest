package com.magdev.domain.weather

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("current")
    val current: Current?,
    @SerializedName("daily")
    val daily: List<Daily>?
): Serializable {

    data class Current(
        @SerializedName("dt")
        val dt: Int?,
        @SerializedName("feels_like")
        val feelsLike: Double?,
        @SerializedName("temp")
        val temp: Double?,
        @SerializedName("weather")
        val weather: List<Weather>?,
    )

    data class Daily(
        @SerializedName("dt")
        val dt: Int?,
        @SerializedName("temp")
        val temp: Temp?,
        @SerializedName("weather")
        val weather: List<Weather>?
    ) {

        data class Temp(
            @SerializedName("day")
            val day: Double?
        )
    }

    data class Weather(
        @SerializedName("icon")
        val icon: String?
    )
}