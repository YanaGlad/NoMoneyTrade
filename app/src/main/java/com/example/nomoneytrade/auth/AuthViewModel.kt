package com.example.nomoneytrade.auth

import androidx.lifecycle.ViewModel
import com.example.nomoneytrade.api.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(val api: Api) : ViewModel() {

}