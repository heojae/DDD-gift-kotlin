package com.example.gift.common.exception

import com.example.gift.common.response.ErrorCode

class InvalidParamException : BaseException {
    constructor() : super(ErrorCode.COMMON_INVALID_PARAMETER)
    constructor(errorCode: ErrorCode) : super(errorCode)
    constructor(errorMsg: String) : super(errorMsg, ErrorCode.COMMON_INVALID_PARAMETER)
    constructor(message: String, errorCode: ErrorCode) : super(message = message, errorCode = errorCode)
}