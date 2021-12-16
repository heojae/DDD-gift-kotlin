package com.example.gift.domain.gift

import com.example.gift.common.exception.IllegalStatusException
import com.example.gift.common.exception.InvalidParamException
import com.example.gift.common.util.TokenGenerator
import com.example.gift.domain.AbstractEntity
import com.example.gift.domain.gift.GiftCommand.Accept
import java.time.ZonedDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "gifts")
class Gift(
    buyerUserId: Long,
    orderToken: String,
    pushType: PushType,
    giftReceiverName: String,
    giftReceiverPhone: String,
    giftMessage: String
) : AbstractEntity() {
    companion object {
        const val GIFT_PREFIX: String = "gt_"
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @field:NotNull(message = "empty giftToken")
    var giftToken: String = TokenGenerator.randomCharacterWithPrefix(GIFT_PREFIX)

    @field:NotNull(message = "empty buyerUserId")
    var buyerUserId: Long = buyerUserId

    @field:NotNull(message = "empty orderToken")
    var orderToken: String = orderToken

    var status: Status = Status.INIT
    var pushType: PushType = pushType
    var giftReceiverName: String = giftReceiverName
    var giftReceiverPhone: String = giftReceiverPhone
    var giftMessage: String = giftMessage

    var receiverName: String? = null
    var receiverPhone: String? = null
    var receiverZipCode: String? = null
    var receiverAddress1: String? = null
    var receiverAddress2: String? = null
    var etcMessage: String? = null

    var paidAt: ZonedDateTime? = null
    var pushedAt: ZonedDateTime? = null
    var acceptedAt: ZonedDateTime? = null
    var expiredAt: ZonedDateTime = ZonedDateTime.now().plusDays(7)


    enum class Status(val description: String) {
        INIT("선물 주문 생성"),
        IN_PAYMENT("결제 중"),
        ORDER_COMPLETE("주문 완료"),
        PUSH_COMPLETE("선물 링크 발송 완료"),
        ACCEPT("선물 수락"),
        DELIVERY_PREPARE("상품준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료"),
        EXPIRATION("선물 수락 만료");
    }

    enum class PushType(val description: String) {
        KAKAO("카카오톡"),
        LMS("문자");
    }

    fun inPayment() {
        if (this.status != Status.INIT) throw IllegalStatusException("Gift inPayment")
        this.status = Status.IN_PAYMENT
    }

    fun completePayment() {
        if (this.status != Status.IN_PAYMENT) throw IllegalStatusException("Gift paymentComplete")
        this.status = Status.ORDER_COMPLETE
        this.paidAt = ZonedDateTime.now()
    }


    fun pushLink() {
        if (this.status != Status.ORDER_COMPLETE) throw IllegalStatusException("Gift inPayment")
        this.status = Status.PUSH_COMPLETE
        this.pushedAt = ZonedDateTime.now()
    }

    fun accept(request: Accept) {
        val receiverName = request.receiverName
        val receiverPhone = request.receiverPhone
        val receiverZipCode = request.receiverZipCode
        val receiverAddress1 = request.receiverAddress1
        val receiverAddress2 = request.receiverAddress2
        val etcMessage = request.etcMessage
        if (!availableAccept()) throw IllegalStatusException()

        status = Status.ACCEPT
        this.receiverName = receiverName
        this.receiverPhone = receiverPhone
        this.receiverZipCode = receiverZipCode
        this.receiverAddress1 = receiverAddress1
        this.receiverAddress2 = receiverAddress2
        this.etcMessage = etcMessage
        acceptedAt = ZonedDateTime.now()
    }

    fun expired() {
        this.status = Status.EXPIRATION
        this.expiredAt = ZonedDateTime.now()
    }

    fun availableAccept(): Boolean {
        if (this.expiredAt.isBefore(ZonedDateTime.now())) return false;
        return this.status == Status.ORDER_COMPLETE || this.status == Status.PUSH_COMPLETE
    }


}