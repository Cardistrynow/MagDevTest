package com.magdev.presentation.common.viper

import moxy.MvpPresenter
import com.github.terrakok.cicerone.Navigator
import javax.inject.Inject
import javax.inject.Provider

abstract class AbstractViperActivity<T : MvpPresenter<*>, N : Navigator> : AbstractNavigationActivity<N>() {

    @Inject protected lateinit var presenterProvider: Provider<T>

    abstract var presenter: T

    protected abstract fun providePresenter(): T
}
