package com.example.nomoneytrade.api.requests

class MakeOfferRequest(
    val postId: Long,
    val city: String,
    val time: String,
    val buyerId: Long,
    val customerId: Long,
    val state: ConditionEnum,
)

enum class ConditionEnum {
    APPROVED,
    REJECTED,
    CONSIDERED
}
