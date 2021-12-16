package com.example.gift.infrastructure.gift.order

import kotlinx.serialization.Serializable

class RetrofitOrderApiResponse {
    @Serializable
    data class Register(
        val orderToken: String
    )
}
