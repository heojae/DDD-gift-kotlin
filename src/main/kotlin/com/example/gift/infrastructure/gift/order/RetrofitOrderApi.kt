package com.example.gift.infrastructure.gift.order

import com.example.gift.common.response.CommonResponse
import com.example.gift.domain.gift.GiftCommand
import com.example.gift.domain.gift.order.OrderApiCommand
import retrofit2.Call
import retrofit2.http.Body;
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitOrderApi {

    @POST("api/v1/gift-orders/init")
    fun registerOrder(@Body request: OrderApiCommand.Register): Call<CommonResponse<RetrofitOrderApiResponse.Register>>

    @POST("api/v1/gift-orders/{orderToken}/update-receiver-info")
    fun updateReceiverInfo(@Path("orderToken") orderToken: String, @Body request: GiftCommand.Accept): Call<Void>
}
