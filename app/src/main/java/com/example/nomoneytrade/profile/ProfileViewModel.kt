package com.example.nomoneytrade.profile

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.profile.entity.User
import com.example.nomoneytrade.stubMyUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val api: Api) : ViewModel() {
    private lateinit var interactionResult: ActivityResultLauncher<Intent>
    val profile = MutableStateFlow(User(-1,"","", "","","","", ""))

    init {
        this.viewModelScope.launch {
            getCurrentUserInfo()
        }
    }

    suspend fun getCurrentUserInfo() {
        //api.getCurrentUserInfo( CURRENT_USER_ID )

        profile.value = stubMyUser
    }

    suspend fun getUserProducts() {
        // api.getUserOffers(CURRENT_USER_ID)
    }
}