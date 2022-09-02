package com.magdev.infrastructure.injection

import javax.inject.Qualifier

//region Module parameters

//endregion

//region Error Handlers

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultErrorHandler

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GlobalErrorHandler

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalErrorHandler

//endregion