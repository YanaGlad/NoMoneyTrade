package com.example.nomoneytrade.api.responses

import kotlinx.serialization.Serializable

@Serializable
class AllOfferResponse(var offers: List<OfferResponse>)
