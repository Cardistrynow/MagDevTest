package com.magdev.infrastructure.injection.modules

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import dagger.multibindings.Multibinds
import com.magdev.infrastructure.errors.handlers.*
import com.magdev.infrastructure.injection.DefaultErrorHandler
import com.magdev.infrastructure.injection.GlobalErrorHandler
import com.magdev.infrastructure.injection.LocalErrorHandler

@Module
interface ErrorHandleModule {

    @Binds
    @IntoSet
    @GlobalErrorHandler
    fun provideInternetConnectionErrorHandler(internetConnectionErrorHandler: InternetConnectionErrorHandler): IErrorHandler

    @Binds
    @IntoSet
    @GlobalErrorHandler
    fun provideJsonSyntaxErrorHandler(jsonSyntaxErrorHandler: JsonSyntaxErrorHandler): IErrorHandler

    @Binds
    @IntoSet
    @GlobalErrorHandler
    fun provideStringResourceErrorHandler(stringResourceErrorHandler: StringResourceErrorHandler): IErrorHandler

    @Binds
    @IntoSet
    @GlobalErrorHandler
    fun provideApiErrorHandler(apiErrorHandler: ApiErrorHandler): IErrorHandler

    @Multibinds
    @LocalErrorHandler
    fun bindEmptyLocalHandlersSet(): Set<IErrorHandler>

    @Binds
    @DefaultErrorHandler
    fun bindDefaultErrorHandler(anyErrorHandler: AnyErrorHandler): IErrorHandler
}
