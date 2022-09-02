package com.magdev.infrastructure.errors.exceptions

import com.magdev.domain.common.BaseResponse

class ApiException(
    val httpCode: Int,
    val errorResponse: BaseResponse?) : RuntimeException()
