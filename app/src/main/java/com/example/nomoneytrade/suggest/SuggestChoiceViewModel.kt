package com.example.nomoneytrade.suggest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.CURRENT_USER_ID
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.api.requests.ConditionEnum
import com.example.nomoneytrade.api.requests.MakeOfferRequest
import com.example.nomoneytrade.api.requests.PostByUser
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.SuggestChoiceEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestChoiceViewModel @Inject constructor(val api: Api) : ViewModel() {

    val event = MutableStateFlow<SuggestChoiceEvent?>(SuggestChoiceEvent.Loading)

    init {
        this.viewModelScope.launch {
            getMyProducts()
        }
    }

    fun clickMakeOffer(product: Product) {
        event.value = SuggestChoiceEvent.Loading
        this.viewModelScope.launch {
            makeOffer(product)
        }
    }

    private suspend fun makeOffer(product: Product) {
        val response = api.newOffer(
            MakeOfferRequest(
                postId = product.id,
                city = product.city,
                time = product.time,
                buyerId = CURRENT_USER_ID,
                customerId = product.userId,
                state = ConditionEnum.CONSIDERED,
            )
        )
        val body = response.body()
        if (response.isSuccessful && body != null) {
            event.value = SuggestChoiceEvent.OfferCreated(body.message)
        } else {
            event.value = SuggestChoiceEvent.Error
        }
    }

    private suspend fun getMyProducts() {
        val response = api.getMyPosts(PostByUser(CURRENT_USER_ID))
        val body = response.body()

        if (response.isSuccessful && body != null) {
            event.value = SuggestChoiceEvent.Success(body.posts.map { dto ->
                Product(
                    id = dto.id,
                    userId = dto.userId,
                    title = dto.title,
                    imageUrl = dto.imagePath,
                    description = dto.description,
                    favourites = false,
                    tags = dto.tags.map { it.tag },
                    exchangeTags = dto.tagsExchange.map { it.tagExchange },
                    city = dto.city ?: "",
                    time = dto.time ?: "",
                )
            })
        } else {
            event.value = SuggestChoiceEvent.Error
        }
    }
}
