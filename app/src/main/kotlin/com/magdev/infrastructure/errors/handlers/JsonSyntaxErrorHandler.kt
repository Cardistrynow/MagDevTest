package com.magdev.infrastructure.errors.handlers

import android.content.Context
import com.google.gson.JsonSyntaxException
import dagger.Lazy
import com.magdev.presentation.common.IBaseView
import javax.inject.Inject

class JsonSyntaxErrorHandler @Inject constructor(private val context: Context, private val lazyView: Lazy<IBaseView>) : IErrorHandler {

    override fun handle(throwable: Throwable): Boolean {
        if (throwable is JsonSyntaxException) {
            throwable.printStackTrace()

            return true
        }

        return false
    }
}
