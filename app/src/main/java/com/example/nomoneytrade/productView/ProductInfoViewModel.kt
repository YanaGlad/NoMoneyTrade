package com.example.nomoneytrade.productView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.CURRENT_USER_ID
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.profile.entity.User
import com.example.nomoneytrade.stubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(private val api: Api) : ViewModel() {

    //TODO everything to state!
    val sellerInfo = MutableStateFlow(User(-1,"","", "","","","", ""))

    init {
        this.viewModelScope.launch {
            getSellerUserInfo(CURRENT_USER_ID)
        }
    }

    suspend fun getSellerUserInfo(id: Int) {
        //api.getUserById(id)
        sellerInfo.value = stubUser
    }
}