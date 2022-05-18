package com.example.nomoneytrade.mvi.state

data class AuthState(
    val username: String = "",
    val password: String = "",
    val email: String = "",
)