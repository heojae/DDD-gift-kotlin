package com.example.gift.domain.gift.order

class OrderApiCommand {

    data class Register(
        val buyerUserId: Long,
        val payMethod: String,
        val orderItemList: List<RegisterOrderItem>
    )


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


}