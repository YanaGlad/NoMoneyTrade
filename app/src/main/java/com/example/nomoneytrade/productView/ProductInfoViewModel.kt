package com.example.nomoneytrade.productView

import androidx.lifecycle.ViewModel
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.profile.entity.Profile
import com.example.nomoneytrade.stubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(private val api: Api) : ViewModel() {

    //TODO everything to state!
    val sellerInfo = MutableStateFlow(Profile(-1,"","", "","","","", ""))

    init {

    }

    suspend fun getSellerUserInfo(id: Int) {
        //api.getUserById(id)
        sellerInfo.value = stubUser
    }
}