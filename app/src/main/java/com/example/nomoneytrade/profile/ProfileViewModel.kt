package com.example.nomoneytrade.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.profile.entity.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val api: Api) : ViewModel() {

    val profile = MutableStateFlow(Profile(-1,"","", "","","","", ""))

    init {
        this.viewModelScope.launch {
            getCurrentUserInfo()
        }
    }

    suspend fun getCurrentUserInfo() {
        //api.getCurrentUserInfo( CURRENT_USER_ID )

        profile.value = Profile(
            id = 12,
            username = "YG",
            fio = "Yana Gladkikh",
            email = "monsterglad12@gmail.COM",
            iconUrl = "https://sun9-77.userapi.com/s/v1/if2/1ePuBSjQkM3Gg3B1dh6Uivsz7SzW7lK6o6fHE0hfqyi-u5AboubP-9jDcA9SYjzVG1l30LEmaOnQlA8znJbP4rRr.jpg?size=1080x898&quality=95&type=album",
            city = "Moscow",
            address = "Propect Verndskogo",
            phoneNumber = "+7(930)410-46-11"
        )
    }

    suspend fun getUserProducts() {
        // api.getUserOffers(CURRENT_USER_ID)
    }
}