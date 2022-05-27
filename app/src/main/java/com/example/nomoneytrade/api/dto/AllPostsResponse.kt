package com.example.nomoneytrade.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AllPostResponse(@SerialName("posts") val posts: List<ProductDto>)
