package com.magdev.presentation.main

import com.magdev.presentation.common.IBaseView
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainView(presenter: MainPresenter): IBaseView {
        return presenter.viewState
    }
}
