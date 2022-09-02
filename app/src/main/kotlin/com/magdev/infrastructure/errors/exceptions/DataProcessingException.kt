package com.magdev.infrastructure.errors.exceptions

class DataProcessingException(cause: Throwable) : Exception(cause) {

    override val message: String?
        get() = "Data processing error"
}
