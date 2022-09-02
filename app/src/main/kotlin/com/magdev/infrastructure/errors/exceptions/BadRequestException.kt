package com.magdev.infrastructure.errors.exceptions

class BadRequestException : Exception() {

    override val message: String?
        get() = "Bad request 400 error"
}