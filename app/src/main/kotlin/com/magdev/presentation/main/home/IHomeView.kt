package com.magdev.presentation.main.home

import com.magdev.domain.weather.WeatherResponse
import com.magdev.presentation.common.IBaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IHomeView : IBaseView {

    fun setPending(isPending: Boolean)

    fun setData(weatherResponse: WeatherResponse)
}
