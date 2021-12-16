package com.example.gift.common.response

import kotlinx.serialization.Serializable


@Serializable
data class CommonResponse<T>(
    private val result: Result? = null,
    private val data: T? = null,
    private val message: String? = null,
    private val errorCode: String? = null
) {
    enum class Result {
        SUCCESS, FAIL
    }
    fun getData(): T? {
        return this.data
    }

    companion object {
        @JvmStatic
        fun <T> success(data: T, message: String?): CommonResponse<T> {
            return CommonResponse(
                result = Result.SUCCESS,
                data = data,
                message = message
            )
        }

        @JvmStatic
        fun <T> success(data: T): CommonResponse<T> {
            return success(data, null)
        }

        @JvmStatic
        fun fail(message: String?, errorCode: String?): CommonResponse<String> {
            return CommonResponse(
                result = Result.FAIL,
                message = message,
                errorCode = errorCode
            )
        }

        @JvmStatic
        fun fail(errorCode: ErrorCode): CommonResponse<String> {
            return CommonResponse(
                result = Result.FAIL,
                message = errorCode.errorMsg,
                errorCode = errorCode.name
            )
        }
    }


}