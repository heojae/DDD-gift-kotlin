package com.example.gift.interfaces.api

import com.example.gift.application.GiftFacade
import com.example.gift.common.response.CommonResponse
import com.example.gift.domain.gift.GiftInfo
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/gifts")
class GiftApiController(
    val giftFacade: GiftFacade,
    val giftDtoMapper: GiftDtoMapper
) {
    @GetMapping("/{giftToken}")
    fun retrieveOrder(@PathVariable giftToken: String): CommonResponse<GiftInfo> {
        val giftInfo = giftFacade.getOrder(giftToken)
        return CommonResponse.success(giftInfo)
    }

    @PostMapping
    fun registerOrder(@RequestBody @Valid request: GiftDto.RegisterReq): CommonResponse<GiftDto.RegisterRes> {
        val command = giftDtoMapper.of(request)
        println("command: $command")
        val giftInfo = giftFacade.registerOrder(command)
        println("giftInfo : $giftInfo")
        return CommonResponse.success(GiftDto.RegisterRes(giftInfo))
    }

    @PostMapping("/{giftToken}/payment-processing")
    fun requestPaymentProcessing(@PathVariable giftToken: String): CommonResponse<String> {
        giftFacade.requestPaymentProcessing(giftToken)
        return CommonResponse.success("OK")
    }

    @PostMapping("/{giftToken}/accept-gift")
    fun acceptGift(
        @PathVariable giftToken: String,
        @RequestBody @Valid request: GiftDto.AcceptGiftReq
    ): CommonResponse<String> {
        val acceptCommand = giftDtoMapper.of(giftToken, request)
        giftFacade.acceptGift(acceptCommand)
        return CommonResponse.success("OK")
    }


}