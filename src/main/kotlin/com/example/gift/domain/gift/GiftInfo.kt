package com.example.gift.domain.gift

import com.example.gift.domain.gift.Gift.PushType

data class GiftInfo(
    val orderToken: String,
    val giftToken: String,
    val pushType: PushType,
    val giftReceiverName: String,
    val giftReceiverPhone: String,
    val giftMessage: String
) {
    constructor(gift: Gift) : this(
        orderToken = gift.orderToken,
        giftToken = gift.giftToken,
        pushType = gift.pushType,
        giftReceiverName = gift.giftReceiverName,
        giftReceiverPhone = gift.giftReceiverPhone,
        giftMessage = gift.giftMessage
    ){}
}