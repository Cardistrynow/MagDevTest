package com.magdev.presentation.main

import com.magdev.infrastructure.injection.ActivityScope
import com.magdev.presentation.Screens
import com.magdev.presentation.common.viper.AbstractViperPresenter
import moxy.InjectViewState
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

@ActivityScope
@InjectViewState
class MainPresenter @Inject constructor(
    private val interactor: MainInteractor,
    private val router: Router) : AbstractViperPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        router.replaceScreen(Screens.Home)
    }
}
