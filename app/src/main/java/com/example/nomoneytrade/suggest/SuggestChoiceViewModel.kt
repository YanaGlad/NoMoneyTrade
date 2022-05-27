package com.example.nomoneytrade.suggest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.alienMug
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.mvi.event.OfferEvent
import com.example.nomoneytrade.mvi.event.SuggestChoiceEvent
import com.example.nomoneytrade.tinkoffBag
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestChoiceViewModel @Inject constructor(val api: Api) : ViewModel() {

    val event = MutableStateFlow<SuggestChoiceEvent>(SuggestChoiceEvent.Loading)

    init {
        this.viewModelScope.launch {
            getMyProducts()
        }
    }

    suspend fun getMyProducts(){
        event.value = SuggestChoiceEvent.Success(
            listOf(
                tinkoffBag,
                alienMug,
            )
        )
        //api.getMyProducts(CURRENT_USER_ID)
    }
}