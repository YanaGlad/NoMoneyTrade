package com.example.nomoneytrade.api.responses

import com.example.nomoneytrade.api.requests.ConditionEnum
import kotlinx.serialization.Serializable

@Serializable
data class OfferResponse(
    val offerId: Long,
    val postId: Long,
    val state: ConditionEnum,
    val buyerId: Long,
    val customerId: Long,
    val city: String,
    val time: String,
)