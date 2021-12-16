package com.example.gift.common.interceptor

import org.slf4j.MDC;
import org.springframework.util.StringUtils
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CommonHttpRequestInterceptor : HandlerInterceptorAdapter() {
    companion object {
        val HEADER_REQUEST_UUID_KEY: String = "x-request-id"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        var requestEventId: String = request.getHeader(HEADER_REQUEST_UUID_KEY)
        if (StringUtils.isEmpty(requestEventId)) {
            requestEventId = UUID.randomUUID().toString()
        }
        MDC.put(HEADER_REQUEST_UUID_KEY, requestEventId)
        return true
    }
}