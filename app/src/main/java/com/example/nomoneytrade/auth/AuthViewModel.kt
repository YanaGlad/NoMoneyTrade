package com.example.nomoneytrade.auth

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.CURRENT_USER_ID
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.api.dto.UserDto
import com.example.nomoneytrade.api.requests.SignInBody
import com.example.nomoneytrade.api.requests.SignUpBody
import com.example.nomoneytrade.mvi.effect.AuthEffect
import com.example.nomoneytrade.mvi.event.AuthEvent
import com.example.nomoneytrade.mvi.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val api: Api) : ViewModel() {

    private val pickImageRequest = 71
    private lateinit var interactionResult: ActivityResultLauncher<Intent>
    var imageFile: MultipartBody.Part? = null

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
            signUp(email, username, password, imageFile)
        }
    }

    fun chooseImage(interactionResult: ActivityResultLauncher<Intent>) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra("requestCode", pickImageRequest)
        interactionResult.launch(Intent.createChooser(intent, "Select avatar"))
    }

    fun navigate() {
        effect.value = AuthEffect.Navigate
    }

    fun navigateShowcase() {
        effect.value = AuthEffect.NavigateShowcase
    }

    private suspend fun signUp(email: String, username: String, password: String, imageBody: MultipartBody.Part?) {
        val signUpBody = SignUpBody(username, email, password)

        val response = api.signUp(signUpBody, imageBody)

        if (response.isSuccessful) {
            val jsonUser = response.body()!!
            state = state.copy(
                user = UserDto(
                    jsonUser.id,
                    jsonUser.username,
                    jsonUser.password,
                    jsonUser.email,
                    jsonUser.isEnabled,
                    jsonUser.imagePath,
                    jsonUser.city,
                    jsonUser.address,
                    jsonUser.phoneNumber,
                )
            )

            CURRENT_USER_ID = jsonUser.id.toLong()

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
                Log.d("Resp", response.message())
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
                user = UserDto(
                    jsonUser.id,
                    jsonUser.username,
                    jsonUser.password,
                    jsonUser.email,
                    jsonUser.isEnabled,
                    jsonUser.imagePath,
                    jsonUser.city,
                    jsonUser.address,
                    jsonUser.phoneNumber,
                )
            )

            CURRENT_USER_ID = jsonUser.id.toLong()

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