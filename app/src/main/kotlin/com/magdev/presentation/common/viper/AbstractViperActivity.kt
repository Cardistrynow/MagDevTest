package com.magdev.presentation.common.viper

import androidx.annotation.LayoutRes
import moxy.MvpPresenter
import com.github.terrakok.cicerone.Navigator
import javax.inject.Inject
import javax.inject.Provider

abstract class AbstractViperActivity<T : MvpPresenter<*>, N : Navigator>(@LayoutRes layoutId: Int) : AbstractNavigationActivity<N>(layoutId) {

    @Inject protected lateinit var presenterProvider: Provider<T>

    abstract var presenter: T

    protected abstract fun providePresenter(): T
}
