package com.example.gift.application

import com.example.gift.domain.gift.GiftCommand
import com.example.gift.domain.gift.GiftInfo
import com.example.gift.domain.gift.GiftService
import org.springframework.stereotype.Service

@Service
class GiftFacade(
    val giftService: GiftService
) {

    fun getOrder(giftToken: String): GiftInfo {
        println("getOrder giftToken = $giftToken")
        return giftService.getGiftInfo(giftToken)
    }

    fun registerOrder(command: GiftCommand.Register): GiftInfo {
        val giftInfo = giftService.registerOrder(command)
        println("giftInfo = $giftInfo")
        return giftInfo
    }

    fun requestPaymentProcessing(giftToken: String) {
        giftService.requestPaymentProcessing(giftToken)
    }

    fun completePayment(orderToken: String) {
        giftService.completePayment(orderToken)
    }

    fun acceptGift(request: GiftCommand.Accept) {
        giftService.acceptGift(request)
    }


}