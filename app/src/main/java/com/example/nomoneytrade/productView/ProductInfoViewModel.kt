package com.example.nomoneytrade.productView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.CURRENT_USER_ID
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.api.UserId
import com.example.nomoneytrade.entity.User
import com.example.nomoneytrade.mvi.event.ProductInfoEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(private val api: Api) : ViewModel() {

    val event = MutableStateFlow<ProductInfoEvent>(ProductInfoEvent.Loading)

    init {
        this.viewModelScope.launch {
            getSellerUserInfo(CURRENT_USER_ID)
        }
    }

    fun getSellerUserInfo(id: Long) {
        this.viewModelScope.launch {
            val response = api.getUserById(UserId(id))
            val body = response.body()
            if (response.isSuccessful && body != null) {
                event.value = ProductInfoEvent.Success(
                    User(
                        id = body.id,
                        username = body.username,
                        fio = "",
                        email = body.email,
                        iconUrl = body.imagePath,
                        phoneNumber = body.phoneNumber
                    )
                )
            } else {
                event.value = ProductInfoEvent.Error
            }
        }
    }
}