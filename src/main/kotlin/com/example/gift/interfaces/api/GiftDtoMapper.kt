package com.example.gift.interfaces.api

import com.example.gift.domain.gift.GiftCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

import org.mapstruct.ReportingPolicy


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface GiftDtoMapper {
    fun of(request: GiftDto.RegisterReq): GiftCommand.Register
    fun of(request: GiftDto.RegisterOrderItemReq): GiftCommand.RegisterOrderItem
    fun of(request: GiftDto.RegisterOrderItemOptionGroupReq): GiftCommand.RegisterOrderItemOptionGroup
    fun of(request: GiftDto.RegisterOrderItemOptionReq): GiftCommand.RegisterOrderItemOption
    fun of(giftToken: String, request: GiftDto.AcceptGiftReq): GiftCommand.Accept
}