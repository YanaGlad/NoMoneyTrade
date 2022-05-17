package com.example.nomoneytrade.api

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("signin")
    suspend fun signIn(
        @Path("username") username: String,
        @Path("password") password: String,
    ): Response<Boolean>

    @POST("signup")
    suspend fun signUp(
        @Path("email") email: String,
        @Path("username") username: String,
        @Path("password") password: String,
    ): Response<Boolean>

    @POST("signout")
    suspend fun signOut(): Response<Boolean>
}