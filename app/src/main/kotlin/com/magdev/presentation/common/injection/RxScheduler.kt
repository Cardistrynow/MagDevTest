package com.magdev.presentation.common.injection

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class RxScheduler {

    open val subscribeOnScheduler: Scheduler =  AndroidSchedulers.mainThread()
    abstract val observeOnScheduler: Scheduler

    open fun getCompletableTransformer(): CompletableTransformer {
        return CompletableTransformer {
            it.subscribeOn(subscribeOnScheduler)
                    .observeOn(observeOnScheduler)
        }
    }

    open fun <T> getObservableTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(subscribeOnScheduler)
                    .observeOn(observeOnScheduler)
        }
    }

    open fun <T> getSingleTransformer(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.subscribeOn(subscribeOnScheduler)
                    .observeOn(observeOnScheduler)
        }
    }
}

class RxSchedulerIOToMain : RxScheduler() {

    override val subscribeOnScheduler: Scheduler = Schedulers.io()
    override val observeOnScheduler: Scheduler = AndroidSchedulers.mainThread()
}

class RxSchedulerComputationToMain : RxScheduler() {

    override val subscribeOnScheduler: Scheduler = Schedulers.computation()
    override val observeOnScheduler: Scheduler = AndroidSchedulers.mainThread()
}
