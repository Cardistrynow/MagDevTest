package com.magdev.infrastructure.errors.handlers

import android.content.Context
import com.magdev.infrastructure.extensions.longToast
import com.magdev.presentation.common.IBaseView
import com.magdev.presentation.common.MessageType
import dagger.Lazy
import javax.inject.Inject

class AnyErrorHandler @Inject constructor(private val context: Context, private val lazyView: Lazy<IBaseView>) : IErrorHandler {

    override fun handle(throwable: Throwable): Boolean {
        val message = throwable.message.toString()
        lazyView.get()?.showMessage(MessageType.TOAST, message) ?: context.longToast(message)

        return true
    }
}
