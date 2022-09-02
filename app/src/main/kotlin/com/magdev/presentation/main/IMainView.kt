package com.magdev.presentation.main

import com.magdev.presentation.common.IBaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMainView : IBaseView
