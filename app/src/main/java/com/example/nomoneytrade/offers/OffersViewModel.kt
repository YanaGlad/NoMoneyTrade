package com.example.nomoneytrade.offers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.CURRENT_USER_ID
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.api.requests.GetOffersRequest
import com.example.nomoneytrade.api.responses.AllOfferResponse
import com.example.nomoneytrade.entity.Offer
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.OfferEvent
import com.example.nomoneytrade.entity.User
import com.example.nomoneytrade.samsungPen
import com.example.nomoneytrade.stubList
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
        clickGetOffersToMe()
    }

    fun clickGetOffersToMe() {
        this.viewModelScope.launch {
            getOffersToMe()
        }
    }

    fun clickGetMyOffers() {
        this.viewModelScope.launch {
            getMyActiveOffers()
        }
    }

    fun clickAcceptOffer() {

    }

    fun clickDeclineOffer() {

    }

    private suspend fun getOffersToMe() {
        event.value =  OfferEvent.LoadedOffers(
            listOf(
                Offer(
                    user = stubMyUser,
                    seller = stubUser,
                    userListing = tinkoffBag,
                    theirListing = samsungPen,
                    place = "Moscow, prospect Vernadskogo",
                    time = "19:00"
                ),
            )
        )
    }

    private suspend fun getMyActiveOffers() {
        event.value =  OfferEvent.LoadedOffers(emptyList())
    }

    //        val response = api.getToMeOffers(
//            GetOffersRequest(CURRENT_USER_ID)
//        )
//        val body = response.body()
//        if (response.isSuccessful && body != null) {
//            event.value = OfferEvent.LoadedOffers(
//                body.mapOffers(CURRENT_USER_ID)
//            )
//        } else {
//            event.value = OfferEvent.Error
//        }

    private fun AllOfferResponse.mapOffers(
        CURRENT_USER_ID: Long,
    ) = this.offers.map {
        with(it) {
            Offer(
                user = User(
                    id = CURRENT_USER_ID,
                    username = "", //TODO
                    fio = "",
                    email = "", //TODO
                    iconUrl = "", //TODO Flow
                    phoneNumber = "", //TODO flow
                ),
                seller = User(
                    id = -1, //seller.id,
                    username = "",
                    fio = "",
                    email = "",
                    iconUrl = "",
                    phoneNumber = "",
                ),
                userListing = Product(
                    id = postId,
                    userId = buyerId,
                    title = "userListing.title",
                    imageUrl = "userListing.imageUrl",
                    description = "",
                    favourites = false,
                    tags = listOf(),
                    exchangeTags = listOf(),
                    city = city,
                    time = time,
                ),
                theirListing = Product(
                    id = -1, //TODO
                    userId = customerId,
                    title = "theirListing.title",
                    imageUrl = "theirListing.imageUrl",
                    description = "",
                    favourites = false,
                    tags = listOf(),
                    exchangeTags = listOf(),
                    city = city,
                    time = time,
                ),
                place = "place",
                time = time,
            )
        }
    }
}