package com.magdev.presentation.common.viper

import androidx.annotation.LayoutRes
import com.magdev.presentation.common.BaseFragment
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Provider

abstract class AbstractViperFragment<T : MvpPresenter<*>>(@LayoutRes layoutId: Int) : BaseFragment(layoutId) {

    @Inject protected lateinit var presenterProvider: Provider<T>

    protected abstract fun providePresenter(): T
}
