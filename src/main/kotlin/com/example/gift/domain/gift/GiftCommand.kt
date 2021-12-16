package com.example.gift.domain.gift


class GiftCommand {

    data class Register(
        val buyerUserId: Long,
        val payMethod: String,
        val pushType: String,
        val giftReceiverName: String,
        val giftReceiverPhone: String,
        val giftMessage: String,
        val orderItemList: List<RegisterOrderItem>
    ){
        fun toEntity(orderToken: String): Gift{
            return Gift(
                buyerUserId=buyerUserId,
                orderToken=orderToken,
                pushType=Gift.PushType.valueOf(pushType),
                giftReceiverName=giftReceiverName,
                giftReceiverPhone=giftReceiverPhone,
                giftMessage=giftMessage
            )
        }
    }

    data class RegisterOrderItem(
        val orderCount: String,
        val itemToken: String,
        val itemName: String,
        val itemPrice: String,
        val orderItemOptionGroupList: List<RegisterOrderItemOptionGroup>
    )

    data class RegisterOrderItemOptionGroup(
        val ordering: Int,
        val itemOptionGroupName: String,
        val orderItemOptionList: List<RegisterOrderItemOption>
    )

    data class RegisterOrderItemOption(
        val ordering: Int,
        val itemOptionName: String,
        val itemOptionPrice: Long
    )


    data class Accept(
        val giftToken: String,
        val receiverName: String,
        val receiverPhone: String,
        val receiverZipCode: String,
        val receiverAddress1: String,
        val receiverAddress2: String,
        val etcMessage: String
    )

}