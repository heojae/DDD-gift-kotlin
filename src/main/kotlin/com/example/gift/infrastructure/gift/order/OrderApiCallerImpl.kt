package com.example.gift.infrastructure.gift.order

import com.example.gift.domain.gift.GiftCommand
import com.example.gift.domain.gift.order.OrderApiCaller
import com.example.gift.domain.gift.order.OrderApiCommand
import com.example.gift.infrastructure.retrofit.RetrofitUtils
import org.springframework.stereotype.Component


@Component
class OrderApiCallerImpl(
    private val retrofitUtils: RetrofitUtils,
    private val retrofitOrderApi: RetrofitOrderApi
) : OrderApiCaller {

    override fun registerGiftOrder(request: OrderApiCommand.Register): String {
        val call = retrofitOrderApi.registerOrder(request)
        println("call : $call")

        try {
            return retrofitUtils.responseSync(call)!!.getData()!!.orderToken
        } catch (e: Exception) {
            throw RuntimeException()
        }
    }

    override fun updateReceiverInfo(orderToken: String, request: GiftCommand.Accept) {
        val call = retrofitOrderApi.updateReceiverInfo(orderToken, request);
        retrofitUtils.responseVoid(call);
    }
}