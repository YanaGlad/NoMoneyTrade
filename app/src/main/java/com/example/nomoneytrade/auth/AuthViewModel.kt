package com.example.nomoneytrade.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.AuthApi
import com.example.nomoneytrade.mvi.effect.AuthEffect
import com.example.nomoneytrade.mvi.event.AuthEvent
import com.example.nomoneytrade.mvi.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    val event = MutableStateFlow<AuthEvent>(AuthEvent.Loading)
    val effect = MutableStateFlow<AuthEffect?>(AuthEffect.None)
    private var state = AuthState(
        email = "",
        username = "",
        password = "",
    )

    fun signUpClick(username: String, password: String) {
        event.value = AuthEvent.Loading
        this.viewModelScope.launch {
            singIn(username, password)
        }
    }

    fun navigateToSignUp() {
        effect.value = AuthEffect.NavigateSignUp
    }

    suspend fun singUp(email: String, username: String, password: String) {
        val response = authApi.signUp(
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
        val response = authApi.signIn(
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
            event.emit(AuthEvent.Error)
        }
    }

    suspend fun logOut() {

    }
}