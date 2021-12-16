package com.example.gift.domain.gift

interface GiftService {
    fun getGiftInfo(giftToken: String): GiftInfo;
    fun registerOrder(request: GiftCommand.Register): GiftInfo;
    fun requestPaymentProcessing(giftToken: String);
    fun completePayment(orderToken: String);
    fun acceptGift(request: GiftCommand.Accept);
}