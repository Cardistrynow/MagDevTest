package com.magdev.infrastructure.errors.handlers

import dagger.Lazy
import com.magdev.infrastructure.errors.exceptions.ApiException
import com.magdev.presentation.common.IBaseView
import com.magdev.presentation.common.MessageType
import javax.inject.Inject

class ApiErrorHandler @Inject constructor(private val lazyView: Lazy<IBaseView>) : IErrorHandler {

    override fun handle(throwable: Throwable): Boolean {
        if (throwable is ApiException) {
            val view = lazyView.get() ?: return false

            val errorMessage = throwable.errorResponse?.message ?: throwable.httpCode.toString()
            view.showMessage(MessageType.TOAST, errorMessage)

            return true
        }

        return false
    }
}