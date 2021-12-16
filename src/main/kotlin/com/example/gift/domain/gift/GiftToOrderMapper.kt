package com.example.gift.domain.gift

import com.example.gift.domain.gift.order.OrderApiCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface GiftToOrderMapper {
    fun of(register: GiftCommand.Register): OrderApiCommand.Register
    fun of(register: GiftCommand.RegisterOrderItem): OrderApiCommand.RegisterOrderItem
    fun of(register: GiftCommand.RegisterOrderItemOptionGroup): OrderApiCommand.RegisterOrderItemOptionGroup
    fun of(register: GiftCommand.RegisterOrderItemOption): OrderApiCommand.RegisterOrderItemOption
}