package com.magdev.presentation.common.viper

import com.magdev.presentation.common.IBaseView
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter

abstract class AbstractViperPresenter<View : IBaseView> : MvpPresenter<View>() {

    protected open val subscriptions = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}
