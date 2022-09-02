package com.magdev.presentation.splash

import dagger.Module
import dagger.Provides
import com.magdev.presentation.common.IBaseView

@Module
class SplashModule {

    @Provides
    fun provideSplashView(presenter: SplashPresenter): IBaseView {
        return presenter.viewState
    }
}