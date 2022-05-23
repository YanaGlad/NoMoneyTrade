package com.example.nomoneytrade.mvi.state

import com.example.nomoneytrade.api.dto.User

data class AuthState(
    val user: User
) {
    constructor() : this(User(0, "", "", "", true))
}