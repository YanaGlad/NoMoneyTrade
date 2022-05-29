package com.example.nomoneytrade.api.requests

import kotlinx.serialization.Serializable

@Serializable
class MakeOfferRequest(
    val postId: Long,
    val city: String,
    val time: String,
    val buyerId: Long,
    val customerId: Long,
    val state: ConditionEnum,
)

@Serializable
enum class ConditionEnum {
    APPROVED,
    REJECTED,
    CONSIDERED
}
