package com.example.nomoneytrade.api

import com.example.nomoneytrade.api.requests.SignInBody
import com.example.nomoneytrade.api.requests.SignUpBody
import com.example.nomoneytrade.api.responses.BaseResponse
import com.example.nomoneytrade.api.dto.User
import okhttp3.MultipartBody
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @Headers("Content-Type: application/json")
    @POST("/auth/signin")
    suspend fun signIn(
        @Body signInBody: SignInBody
    ): Response<User>

    @Headers("Content-Type: application/json")
    @POST("/auth/signup")
    suspend fun signUp(
        @Part signUpBody: SignUpBody,
        @Part image: MultipartBody.Part?
    ): Response<User>

    @POST("/auth/signout")
    suspend fun signOut(): Response<BaseResponse>

    @Multipart
    @POST("/api/put_image")
    suspend fun putImage(@Part image: MultipartBody.Part): Response<BaseResponse>
}