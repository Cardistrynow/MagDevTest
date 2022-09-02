package com.magdev.presentation.splash

import com.github.terrakok.cicerone.Router
import com.magdev.infrastructure.errors.ErrorResolver
import com.magdev.infrastructure.injection.ActivityScope
import com.magdev.presentation.Screens
import com.magdev.presentation.common.viper.AbstractViperPresenter
import io.reactivex.rxkotlin.plusAssign
import moxy.InjectViewState
import javax.inject.Inject

@ActivityScope
@InjectViewState
class SplashPresenter @Inject constructor(
    private val interactor: SplashInteractor,
    private val router: Router,
    private val errorResolver: ErrorResolver) : AbstractViperPresenter<ISplashView>() {

    private fun getMockData() {
        subscriptions += interactor.getMockData()
            .subscribe(
                {
                    navigateToHome()
                },
                { error ->
                    errorResolver.resolve(error)
                }
            )
    }

    fun checkPermissions() {
        viewState.checkPermissions()
    }

    fun onPermissionsResult(isGranted: Boolean, shouldShowRationale: Boolean = false) {
        when {
            isGranted -> getMockData()
            shouldShowRationale -> getMockData()
            else -> viewState.showPermissionsRationale()
        }
    }

    fun onSettingsClick() {
        viewState.navigateToSettings()
    }

    private fun navigateToHome() {
        router.newRootScreen(Screens.Main)
    }
}
