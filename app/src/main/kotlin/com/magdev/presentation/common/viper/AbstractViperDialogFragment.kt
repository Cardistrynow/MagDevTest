package com.magdev.presentation.common.viper

import com.magdev.presentation.common.BaseDialogFragment
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Provider

abstract class AbstractViperDialogFragment<T : MvpPresenter<*>> : BaseDialogFragment() {

    @Inject protected lateinit var presenterProvider: Provider<T>

    protected abstract fun providePresenter(): T
}
