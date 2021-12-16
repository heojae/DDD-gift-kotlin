package com.example.gift.infrastructure.retrofit

import com.example.gift.infrastructure.gift.order.RetrofitOrderApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit


@Configuration
class RetrofitServiceRegistry {
    @Value("\${example.order.base-url}")
    private lateinit var baseUrl: String

    @Bean
    fun retrofitOrderApi(): RetrofitOrderApi {
        val retrofit: Retrofit = RetrofitUtils.initRetrofit(this.baseUrl)
        return retrofit.create(RetrofitOrderApi::class.java)
    }
}