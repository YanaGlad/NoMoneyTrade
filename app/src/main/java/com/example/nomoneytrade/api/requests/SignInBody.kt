package com.example.nomoneytrade.api.requests;

import kotlinx.serialization.Serializable

@Serializable
class SignInBody(
    val login: String,
    val password: String
)
