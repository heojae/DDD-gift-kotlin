package com.example.gift.common.exception

import com.example.gift.common.response.ErrorCode


open class BaseException : RuntimeException {
    var errorCode: ErrorCode? = null

    constructor() {}
    constructor(errorCode: ErrorCode) : super(errorCode.errorMsg) {
        this.errorCode = errorCode
    }

    constructor(message: String, errorCode: ErrorCode) : super(message) {
        this.errorCode = errorCode
    }

    constructor(message: String, errorCode: ErrorCode, cause: Throwable) : super(message, cause) {
        this.errorCode = errorCode
    }
}