package com.example.gift.infrastructure.gift

import com.example.gift.domain.gift.Gift
import com.example.gift.domain.gift.GiftStore
import org.springframework.stereotype.Component


@Component
class GiftStoreImpl(
    private val giftRepository: GiftRepository
) : GiftStore {
    override fun store(gift: Gift): Gift {
        return this.giftRepository.save(gift)
    }
}
