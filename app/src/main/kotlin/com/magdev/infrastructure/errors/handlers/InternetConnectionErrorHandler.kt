package com.magdev.infrastructure.errors.handlers

import dagger.Lazy
import com.magdev.R
import com.magdev.infrastructure.errors.exceptions.InternetConnectionException
import com.magdev.presentation.common.IBaseView
import com.magdev.presentation.common.MessageType
import javax.inject.Inject

class InternetConnectionErrorHandler @Inject constructor(private val lazyView: Lazy<IBaseView>) : IErrorHandler {

    override fun handle(throwable: Throwable): Boolean {
        if (throwable is InternetConnectionException) {
            val view = lazyView.get() ?: return false
            view.showMessage(MessageType.TOAST, R.string.no_internet_connection_exception)

            return true
        }

        return false
    }
}