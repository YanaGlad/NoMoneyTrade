package com.example.nomoneytrade.mvi.effect

sealed class AuthEffect {
    object None: AuthEffect()
    object NavigateShowcase: AuthEffect()
    object Navigate: AuthEffect()
}