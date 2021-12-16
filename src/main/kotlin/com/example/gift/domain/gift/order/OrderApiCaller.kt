package com.example.gift.domain.gift.order

import com.example.gift.domain.gift.GiftCommand

interface OrderApiCaller {
    fun registerGiftOrder(request: OrderApiCommand.Register): String;
    fun updateReceiverInfo(orderToken: String, request: GiftCommand.Accept);
}