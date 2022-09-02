package com.magdev.domain.common

import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("unique error number")
    val uniqueErrorNumber: String? = null

    @SerializedName("error")
    val error: String? = null

    @SerializedName("message")
    val message: String? = null

    @SerializedName("detail")
    val detail: String? = null

}