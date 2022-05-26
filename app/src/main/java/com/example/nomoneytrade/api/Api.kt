package com.example.nomoneytrade.api

import com.example.nomoneytrade.api.requests.SignInBody
import com.example.nomoneytrade.api.requests.SignUpBody
import com.example.nomoneytrade.api.responses.BaseResponse
import com.example.nomoneytrade.api.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @Headers("Content-Type: application/json")
    @POST("/auth/signin")
    suspend fun signIn(
        @Body signInBody: SignInBody
    ): Response<UserDto>

    @Headers("Content-Type: application/json")
    @POST("/auth/signup")
    suspend fun signUp(
        @Body signUpBody: SignUpBody
    ): Response<UserDto>

    @POST("/auth/signout")
    suspend fun signOut(): Response<BaseResponse>
}