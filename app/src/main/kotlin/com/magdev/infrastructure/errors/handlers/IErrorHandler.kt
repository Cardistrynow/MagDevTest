package com.magdev.infrastructure.errors.handlers

interface IErrorHandler {

    fun handle(throwable: Throwable): Boolean
}