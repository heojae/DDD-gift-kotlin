package com.example.gift.interfaces.message

import com.example.gift.application.GiftFacade
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class GiftSqsMessageListener(
    val giftFacade: GiftFacade
) {
    @SqsListener(value = ["order-payComplete-live.fifo"], deletionPolicy = SqsMessageDeletionPolicy.NO_REDRIVE)
    fun readMessage(message: GiftPaymentCompleteMessage) {
        val orderToken = message.orderToken;
        println("[GiftSqsMessageListener.readMessage] orderToken = $orderToken")
        giftFacade.completePayment(orderToken)
    }
}
