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

    private suspend fun getOffersToMe(){
        val response = api.getToMeOffers(
            GetOffersRequest(CURRENT_USER_ID)
        )
        val body = response.body()
        if(response.isSuccessful && body!=null) {
            event.value = OfferEvent.LoadedOffers(
                body.mapOffers(CURRENT_USER_ID)
            )
        }else{
            event.value = OfferEvent.Error
        }
    }

    private suspend fun getMyActiveOffers() {
        event.value = OfferEvent.Loading
        val response = api.getMyOffers(
            GetOffersRequest(CURRENT_USER_ID)
        )
        val body = response.body()
        if(response.isSuccessful && body!=null) {
            event.value = OfferEvent.LoadedOffers(
                body.mapOffers(CURRENT_USER_ID)
            )
        }else{
            event.value = OfferEvent.Error
        }

       // val response = api.getActiveOffers() TODO
//        event.value = OfferEvent.LoadedOffers(
//            offers = listOf(
//                Offer(
//                    user = stubMyUser,
//                    seller = stubUser,
//                    userListing = tinkoffBag,
//                    theirListing = samsungPen,
//                    place = "Sydney",
//                    time = "4 AM"
//                ),
//                Offer(
//                    user = stubMyUser,
//                    seller = stubUser,
//                    userListing = tinkoffBag,
//                    theirListing = samsungPen,
//                    place = "Moscow",
//                    time = "2 AM"
//                )
//            )
//        )
    }

    private fun AllOfferResponse.mapOffers(
        CURRENT_USER_ID: Long,
    ) = this.offers.map {
        with(it) {
            Offer(
                user = User(
                    id = CURRENT_USER_ID,
                    username = user.username,
                    fio = "",
                    email = user.email,
                    iconUrl = user.imagePath,
                    phoneNumber = user.phoneNumber,
                ),
                seller = User(
                    id = seller.id,
                    username = seller.username,
                    fio = "",
                    email = seller.email,
                    iconUrl = seller.imagePath,
                    phoneNumber = seller.phoneNumber,
                ),
                userListing = Product(
                    id = userListing.id,
                    userId = userListing.userId,
                    title = userListing.title,
                    imageUrl = userListing.imageUrl,
                    description = userListing.description,
                    favourites = userListing.favourites,
                    tags = userListing.tags,
                    exchangeTags = userListing.exchangeTags
                ),
                theirListing = Product(
                    id = theirListing.id,
                    userId = theirListing.userId,
                    title = theirListing.title,
                    imageUrl = theirListing.imageUrl,
                    description = theirListing.description,
                    favourites = theirListing.favourites,
                    tags = theirListing.tags,
                    exchangeTags = theirListing.exchangeTags
                ),
                place = place,
                time = time,
            )
        }
    }
}