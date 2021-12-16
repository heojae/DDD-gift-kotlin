package com.example.gift.interfaces.api

import com.example.gift.domain.gift.GiftInfo
import kotlinx.serialization.Serializable

class GiftDto {

    @Serializable
    data class RegisterReq(
        val buyerUserId: Long,
        val payMethod: String,
        val pushType: String,
        val giftReceiverName: String,
        val giftReceiverPhone: String,
        val giftMessage: String,
        val orderItemList: List<RegisterOrderItemReq>
    )

    @Serializable
    data class RegisterOrderItemReq(
        val orderCount: Int,
        val itemToken: String,
        val itemName: String,
        val itemPrice: Long,
        val orderItemOptionGroupList: List<RegisterOrderItemOptionGroupReq>
    )

    @Serializable
    data class RegisterOrderItemOptionGroupReq(
        val ordering: Int,
        val itemOptionGroupName: String,
        val orderItemOptionList: List<RegisterOrderItemOptionReq>
    )

    @Serializable
    data class RegisterOrderItemOptionReq(
        val ordering: Int,
        val itemOptionName: String,
        val itemOptionPrice: Long
    )

    @Serializable
    data class RegisterRes(
        val orderToken: String,
        val giftToken: String
    ) {
        constructor(giftInfo: GiftInfo) : this(
            orderToken = giftInfo.orderToken,
            giftToken = giftInfo.giftToken
        )
    }


    @Serializable
    data class AcceptGiftReq(
        val receiverName: String,
        val receiverPhone: String,
        val receiverZipCode: String,
        val receiverAddress1: String,
        val receiverAddress2: String,
        val etcMessage: String
    )
}