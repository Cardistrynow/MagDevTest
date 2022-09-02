package com.magdev.presentation.common

import androidx.annotation.StringRes

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface IBaseView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(messageType: MessageType = MessageType.TOAST, message: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMessage(messageType: MessageType = MessageType.TOAST, @StringRes resId: Int, vararg args: String)
}
