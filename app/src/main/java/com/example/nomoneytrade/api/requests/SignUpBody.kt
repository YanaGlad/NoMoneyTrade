package com.example.nomoneytrade.api.requests;

import kotlinx.serialization.Serializable

@Serializable
class SignUpBody(
    val username: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
)
