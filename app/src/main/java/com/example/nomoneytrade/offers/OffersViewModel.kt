package com.example.nomoneytrade.offers

import androidx.lifecycle.ViewModel
import com.example.nomoneytrade.api.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(private val api: Api) : ViewModel() {

}