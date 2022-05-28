package com.example.nomoneytrade.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.CURRENT_USER_ID
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.api.UserId
import com.example.nomoneytrade.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val api: Api) : ViewModel() {

    //TODO заменить на стейт!
    val profile = MutableStateFlow(User(-1, "", "", "", "", "",))

    init {
        this.viewModelScope.launch {
            getCurrentUserInfo()
        }
    }

    suspend fun getCurrentUserInfo() {
        val response = api.getUserById(UserId(CURRENT_USER_ID))
        val body = response.body()
        if (response.isSuccessful && body != null) {
            profile.value = User(
                id = CURRENT_USER_ID,
                username = body.username,
                fio = "Гладких Яна Сергеевна",
                email = body.email,
                iconUrl = body.imagePath,
                phoneNumber = body.phoneNumber,
            )
        } else {

        }
    }

    suspend fun getUserProducts() {
        // api.getUserOffers(CURRENT_USER_ID)
    }
}