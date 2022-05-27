package com.example.nomoneytrade.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.entity.Offer
import com.example.nomoneytrade.mvi.event.OfferEvent
import com.example.nomoneytrade.samsungPen
import com.example.nomoneytrade.stubMyUser
import com.example.nomoneytrade.stubUser
import com.example.nomoneytrade.tinkoffBag
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(private val api: Api) : ViewModel() {

    val event = MutableStateFlow<OfferEvent>(OfferEvent.Loading)

    init {
        this.viewModelScope.launch {
            getActiveOffers()
        }
    }

    suspend fun getUserById(id: Int) {

    }

    suspend fun getActiveOffers() {


        event.value = OfferEvent.Loading
       // val response = api.getActiveOffers() TODO
        event.value = OfferEvent.LoadedOffers(
            offers = listOf(
                Offer(
                    user = stubMyUser,
                    seller = stubUser,
                    userListing = tinkoffBag,
                    theirListing = samsungPen,
                    place = "Sydney",
                    time = "4 AM"
                ),
                Offer(
                    user = stubMyUser,
                    seller = stubUser,
                    userListing = tinkoffBag,
                    theirListing = samsungPen,
                    place = "Moscow",
                    time = "2 AM"
                )
            )
        )
    }
}