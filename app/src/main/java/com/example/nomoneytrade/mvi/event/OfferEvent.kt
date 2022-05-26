package com.example.nomoneytrade.mvi.event

import com.example.nomoneytrade.entity.Offer

sealed class OfferEvent {
    object Loading: OfferEvent()
    class LoadedOffers(val offers: List<Offer>): OfferEvent()
    object Error: OfferEvent()
}