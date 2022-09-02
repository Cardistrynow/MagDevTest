package com.magdev.presentation.main.home

import com.magdev.presentation.common.IBaseView
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideHomeView(presenter: HomePresenter): IBaseView {
        return presenter.viewState
    }
}
