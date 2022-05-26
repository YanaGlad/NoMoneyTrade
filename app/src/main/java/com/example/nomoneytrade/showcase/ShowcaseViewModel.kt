package com.example.nomoneytrade.showcase

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.nomoneytrade.mvi.event.ShowcaseEvent
import com.example.nomoneytrade.stubList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ShowcaseViewModel @Inject constructor() : ViewModel()  {

    val event = MutableStateFlow<ShowcaseEvent>(ShowcaseEvent.Loading)

    init {

        //Stub!
        event.value = ShowcaseEvent.Success(
            stubList
        )
        stubList.forEach {
            Log.d("TATAT", "Len: ${it.description.length}")
        }
    }

    suspend fun loadProducts() {

    }
}