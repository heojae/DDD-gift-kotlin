package com.example.gift.infrastructure.gift

import com.example.gift.common.exception.EntityNotFoundException
import com.example.gift.common.exception.InvalidParamException
import com.example.gift.domain.gift.Gift
import com.example.gift.domain.gift.GiftReader
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class GiftReaderImpl(
    private val giftRepository: GiftRepository
) : GiftReader {
    override fun getGiftBy(giftToken: String): Gift {
        if (StringUtils.isEmpty(giftToken)) throw InvalidParamException()
        return this.giftRepository.findByGiftToken(giftToken) ?: throw EntityNotFoundException()
    }

    override fun getGiftByOrderToken(orderToken: String): Gift {
        if (StringUtils.isEmpty(orderToken)) throw InvalidParamException()
        return this.giftRepository.findByOrderToken(orderToken) ?: throw EntityNotFoundException()
    }
}