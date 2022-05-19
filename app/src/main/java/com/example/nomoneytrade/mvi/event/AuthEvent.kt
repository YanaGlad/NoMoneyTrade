package com.example.nomoneytrade.mvi.event

import com.example.nomoneytrade.mvi.effect.AuthEffect
import com.example.nomoneytrade.mvi.state.AuthState

//TODO асбтракция над Event, всегда принимает State и Effect (Абстракции над state и effect)

sealed class AuthEvent {
    class Success(
        state: AuthState,
        effect: AuthEffect,
    ): AuthEvent()
    object Loading: AuthEvent()
    object FailedToLogin: AuthEvent()
    object Error: AuthEvent()
}