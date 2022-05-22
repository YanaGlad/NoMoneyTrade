package com.example.nomoneytrade.auth

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.api.requests.SignInBody
import com.example.nomoneytrade.api.requests.SignUpBody
import com.example.nomoneytrade.auth.entity.User
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

    private var state = AuthState()

    fun signInClick(username: String, password: String) {
        event.value = AuthEvent.Loading
        this.viewModelScope.launch {
            signIn(username, password)
        }
    }

    fun signUpClick(username: String, password: String, email: String) {
        event.value = AuthEvent.Loading
        this.viewModelScope.launch {
            signUp(email, username, password)
        }
    }

    fun navigate() {
        effect.value = AuthEffect.Navigate
    }

    fun navigateShowcase() {
        effect.value = AuthEffect.NavigateShowcase
    }

    private suspend fun signUp(email: String, username: String, password: String) {
        val signUpBody = SignUpBody(username, email, password)

        val response = api.signUp(signUpBody)

        if (response.isSuccessful) {
            val jsonUser = response.body()!!
            state = state.copy(
                user = User(
                    jsonUser.id,
                    jsonUser.username,
                    jsonUser.password,
                    jsonUser.email,
                    jsonUser.isEnabled
                )
            )

            event.emit(
                AuthEvent.Success(
                    state = state,
                    effect = AuthEffect.NavigateShowcase
                )
            )
        } else {
            Log.d("Resp", response.code().toString())
            if (response.code() == 400) {
                // bad request: collision
                event.emit(AuthEvent.FailedToLogin)
            } else {
                event.emit(AuthEvent.Error)
            }
        }
    }

    private suspend fun signIn(login: String, password: String) {
        val signInBody = SignInBody(login, password)
        val response = api.signIn(signInBody)
        if (response.isSuccessful) {
            val jsonUser = response.body()!!
            state = state.copy(
                user = User(
                    jsonUser.id,
                    jsonUser.username,
                    jsonUser.password,
                    jsonUser.email,
                    jsonUser.isEnabled
                )
            )

            event.emit(
                AuthEvent.Success(
                    state = state,
                    effect = AuthEffect.NavigateShowcase //TODO
                )
            )
        } else {
            if (response.code() == 401) {
                // unauthorised
                event.emit(AuthEvent.FailedToLogin)
            } else {
                event.emit(AuthEvent.Error)
            }
        }
    }

    suspend fun logOut() {

    }
}