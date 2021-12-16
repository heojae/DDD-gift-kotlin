package com.example.gift.interfaces.message

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable


@Serializable
data class GiftPaymentCompleteMessage(
    @JsonProperty("orderToken")
    val orderToken: String
)