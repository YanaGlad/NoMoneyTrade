package com.example.nomoneytrade.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TagExchangeDto (
    @SerialName("tag_exchange") val tagExchange: String,
)