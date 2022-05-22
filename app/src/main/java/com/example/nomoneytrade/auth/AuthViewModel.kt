package com.example.nomoneytrade.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.mvi.effect.AuthEffect
import com.example.nomoneytrade.mvi.event.AuthEvent
import com.example.nomoneytrade.mvi.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val api: Api) : ViewModel() {

    val event = MutableStateFlow<AuthEvent>(AuthEvent.None)
    val effect = MutableStateFlow<AuthEffect?>(AuthEffect.None)

    private var state = AuthState(
        email = "",
        username = "",
        password = "",
    )

    fun signInClick(username: String, password: String) {
        event.value = AuthEvent.Loading
        this.viewModelScope.launch {
            singIn(username, password)
        }
    }

    fun signUpClick(username: String, password: String, email: String) {
        event.value = AuthEvent.Loading
        this.viewModelScope.launch {
            singUp(username, password, email)
        }
    }

    fun navigate() {
        effect.value = AuthEffect.Navigate
    }

    private suspend fun singUp(email: String, username: String, password: String) {
        val response = api.signUp(
            email = email,
            username = username,
            password = password,
        )

        if (response.isSuccessful) {
            state = state.copy(username = username, password = password, email = email)

            event.emit(
                AuthEvent.Success(
                    state = state,
                    effect = AuthEffect.NavigateShowcase
                )
            )
        } else {
            event.emit(AuthEvent.Error)
        }
    }

    suspend fun singIn(login: String, password: String) {
        val response = api.signIn(
            login = login,
            password = password,
        )
        if (response.isSuccessful) {
            state = state.copy(username = login, password = password)
            event.emit(
                AuthEvent.Success(
                    state = state,
                    effect = AuthEffect.NavigateShowcase
                )
            )
        } else {
            event.emit(AuthEvent.FailedToLogin)
        }
    }

    suspend fun logOut() {

    }
}