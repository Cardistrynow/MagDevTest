package com.magdev.infrastructure.network.interceptors

import com.magdev.infrastructure.errors.ApiExceptionFactory
import com.magdev.infrastructure.errors.exceptions.InternetConnectionException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorInterceptor(private val apiExceptionFactory: ApiExceptionFactory) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val response: Response

        try {
            response = chain.proceed(chain.request())

            if (!response.isSuccessful) {
                throw apiExceptionFactory.fromResponse(response)
            }

        } catch(e: IOException) {
            throw InternetConnectionException(e)
        }

        return response
    }
}
