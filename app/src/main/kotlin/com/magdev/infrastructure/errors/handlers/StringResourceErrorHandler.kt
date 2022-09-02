package com.magdev.infrastructure.errors.handlers

import com.magdev.infrastructure.errors.exceptions.StringResourceException
import com.magdev.presentation.common.IBaseView
import com.magdev.presentation.common.MessageType
import dagger.Lazy
import javax.inject.Inject

class StringResourceErrorHandler @Inject constructor(private val lazyViewState: Lazy<IBaseView>) : IErrorHandler {

    override fun handle(throwable: Throwable): Boolean {
        if (throwable is StringResourceException) {
            val viewState = lazyViewState.get() ?: return false
            viewState.showMessage(MessageType.TOAST, throwable.stringResourceId)

            return true
        }

        return false
    }
}