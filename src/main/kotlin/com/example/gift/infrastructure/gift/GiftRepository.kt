package com.example.gift.infrastructure.gift

import com.example.gift.domain.gift.Gift
import org.springframework.data.jpa.repository.JpaRepository

interface GiftRepository : JpaRepository<Gift, Long> {
    fun findByGiftToken(giftToken: String): Gift?
    fun findByOrderToken(orderToken: String): Gift?
}