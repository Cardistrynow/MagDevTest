package com.magdev.presentation.main.home

import android.location.Location
import com.github.terrakok.cicerone.Router
import com.magdev.infrastructure.errors.ErrorResolver
import com.magdev.infrastructure.injection.FragmentScope
import com.magdev.presentation.common.viper.AbstractViperPresenter
import io.reactivex.rxkotlin.plusAssign
import moxy.InjectViewState
import javax.inject.Inject

@FragmentScope
@InjectViewState
class HomePresenter @Inject constructor(
    private val interactor: HomeInteractor,
    private val router: Router,
    private val errorResolver: ErrorResolver) : AbstractViperPresenter<IHomeView>() {

    fun getWeather(location: Location?) {
        subscriptions += interactor.getWeather(location)
            .doOnSubscribe { viewState.setPending(true) }
            .doFinally { viewState.setPending(false) }
            .subscribe(
                {
                    viewState.setData(it)
                },
                { error ->
                    errorResolver.resolve(error)
                }
            )
    }
}
