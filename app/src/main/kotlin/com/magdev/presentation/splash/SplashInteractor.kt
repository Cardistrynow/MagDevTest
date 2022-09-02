package com.magdev.presentation.splash

import com.magdev.presentation.common.injection.DomainModule.Companion.ioToUI
import com.magdev.presentation.common.injection.RxScheduler
import io.reactivex.Completable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class SplashInteractor @Inject constructor(@Named(ioToUI) private val rxScheduler: RxScheduler) {

    fun getMockData(): Completable {
        return Completable.complete().delay(1000, TimeUnit.MILLISECONDS)
            .compose(rxScheduler.getCompletableTransformer())
    }
}