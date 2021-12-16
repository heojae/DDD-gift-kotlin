package com.example.gift.common.exception

import com.example.gift.common.response.ErrorCode

class EntityNotFoundException : BaseException {
    constructor() : super(ErrorCode.COMMON_INVALID_PARAMETER) {}
    constructor(message: String) : super(message = message, ErrorCode.COMMON_INVALID_PARAMETER) {}
}