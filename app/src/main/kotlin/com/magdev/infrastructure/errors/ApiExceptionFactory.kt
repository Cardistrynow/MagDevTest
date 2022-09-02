package com.magdev.infrastructure.errors

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.magdev.domain.common.BaseResponse
import com.magdev.infrastructure.errors.exceptions.*
import okhttp3.Response

class ApiExceptionFactory {

    companion object {
        private const val UNKNOWN_MESSAGE = "Unknown error"
        private const val UNKNOWN_SERVER_ERROR_MESSAGE = "Unknown server error"
    }

    fun fromResponse(response: Response): Throwable {
        return try {
            val baseResponse = Gson().fromJson(response.body?.string(), BaseResponse::class.java)
            when (response.code) {
                400 -> BadRequestException()
                401 -> UnauthenticatedException(baseResponse.message ?: baseResponse.detail ?: "401 error")
                404 -> NotFoundException()
                in 400..499 -> ApiException(response.code, baseResponse)
                in 500..599 -> ServerException("${response.code}: $UNKNOWN_SERVER_ERROR_MESSAGE")
                else -> UncheckedException(UNKNOWN_MESSAGE)
            }
        } catch (e: JsonSyntaxException) {
            DataProcessingException(e)
        }
    }
}
