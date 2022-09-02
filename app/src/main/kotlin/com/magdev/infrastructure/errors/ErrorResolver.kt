package com.magdev.infrastructure.errors

import com.magdev.infrastructure.errors.handlers.IErrorHandler
import com.magdev.infrastructure.injection.DefaultErrorHandler
import com.magdev.infrastructure.injection.GlobalErrorHandler
import com.magdev.infrastructure.injection.LocalErrorHandler
import javax.inject.Inject

class ErrorResolver @Inject constructor(

    @LocalErrorHandler
    private val localHandlers: Set<@JvmSuppressWildcards IErrorHandler>,

    @GlobalErrorHandler
    private val globalHandlers: Set<@JvmSuppressWildcards IErrorHandler>,

    @DefaultErrorHandler
    private val defaultHandler: IErrorHandler) {

    fun resolve(throwable: Throwable) {
        if (localHandlers.any { it.handle(throwable) }) return
        if (globalHandlers.any { it.handle(throwable) }) return

        defaultHandler.handle(throwable)
    }
}
