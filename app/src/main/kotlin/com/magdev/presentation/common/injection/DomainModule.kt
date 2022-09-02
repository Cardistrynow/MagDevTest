package com.magdev.presentation.common.injection

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DomainModule {

    companion object {
        const val ioToUI = "ioToUI"
        const val computationToUI = "computationToUI"
    }

    @Provides
    @Singleton
    @Named(ioToUI)
    fun provideIOToMainRxSchedulers(): RxScheduler {
        return RxSchedulerIOToMain()
    }

    @Provides
    @Singleton
    @Named(computationToUI)
    fun provideComputationToMainRxSchedulers(): RxScheduler {
        return RxSchedulerComputationToMain()
    }
}
