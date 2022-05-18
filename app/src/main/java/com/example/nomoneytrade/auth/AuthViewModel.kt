package com.example.nomoneytrade.auth

import androidx.lifecycle.ViewModel
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.mvi.effect.AuthEffect
import com.example.nomoneytrade.mvi.event.AuthEvent
import com.example.nomoneytrade.mvi.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(val api: Api) : ViewModel() {

    val state = MutableStateFlow<AuthEvent>(AuthEvent.Loading)
    val effect = MutableStateFlow<AuthEffect?>(AuthEffect.None)

    fun signUpClick() {
        effect.value = AuthEffect.NavigateSignUp
    }

    suspend fun singUp(email: String, username: String, password: String) {
        val response = api.signUp(
            email = email,
            username = username,
            password = password,
        )

        if (response.isSuccessful) {
            state.emit(
                AuthEvent.Success(
                    state = AuthState(
                        email = email,
                        username = username,
                        password = password,
                    ),
                    effect = AuthEffect.NavigateShowcase
                )
            )
        } else {
            state.emit(AuthEvent.Fail)
        }
    }

    suspend fun singIn(login: String, password: String) {
        val response = api.signIn(
            login = login,
            password = password,
        )
        if (response.isSuccessful) {

        } else {

        }
    }

    suspend fun signOut() {

    }
}