package com.example.nomoneytrade.profile

import androidx.lifecycle.ViewModel
import com.example.nomoneytrade.api.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val api: Api) : ViewModel() {

}