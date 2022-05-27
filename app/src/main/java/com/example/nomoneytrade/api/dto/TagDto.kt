package com.example.nomoneytrade.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TagDto (
    @SerialName("tag") val tag: String,
)