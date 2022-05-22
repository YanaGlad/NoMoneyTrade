package com.example.nomoneytrade.mvi.state

import com.example.nomoneytrade.auth.entity.User

data class AuthState(
    val user: User
) {
    constructor() : this(User(0, "", "", "", true))
}