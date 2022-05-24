package com.example.nomoneytrade.mvi.event

import com.example.nomoneytrade.profile.entity.Profile

sealed class ProfileEvent {
    class Success(state: Profile): ProfileEvent() //Loaded products + profile info into state!
    object Error: ProfileEvent()
    object Loading: ProfileEvent()
}
