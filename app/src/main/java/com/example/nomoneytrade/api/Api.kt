package com.example.nomoneytrade.api

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("/auth/signin")
    suspend fun signIn(
        @Path("login") login: String,
        @Path("password") password: String,
    ): Response<Boolean>

    @POST("/auth/signup")
    suspend fun signUp(
        @Path("email") email: String,
        @Path("username") username: String,
        @Path("password") password: String,
    ): Response<Boolean>

    @POST("/auth/signout")
    suspend fun signOut(): Response<Boolean>
}