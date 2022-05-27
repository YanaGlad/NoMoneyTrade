package com.example.nomoneytrade.mvi.event

import com.example.nomoneytrade.entity.Product

sealed class SuggestChoiceEvent {
    object Error: SuggestChoiceEvent()
    object Loading: SuggestChoiceEvent()
    class Success(val availableProducts: List<Product>): SuggestChoiceEvent()
}