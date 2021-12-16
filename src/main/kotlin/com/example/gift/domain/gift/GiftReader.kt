package com.example.gift.domain.gift

interface GiftReader {
    fun getGiftBy(giftToken: String): Gift;
    fun getGiftByOrderToken(orderToken: String): Gift;
}