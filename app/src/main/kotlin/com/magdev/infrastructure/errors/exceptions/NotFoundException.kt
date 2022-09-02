package com.magdev.infrastructure.errors.exceptions

class NotFoundException : Exception() {

    override val message: String?
        get() = "Not found error"
}