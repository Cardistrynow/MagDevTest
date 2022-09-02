package com.magdev.presentation.splash

import com.magdev.presentation.common.IBaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ISplashView : IBaseView {

    fun checkPermissions()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPermissionsRationale()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToSettings()
}
