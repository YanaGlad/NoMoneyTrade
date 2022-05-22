package com.example.nomoneytrade.api

import com.example.nomoneytrade.auth.entity.User
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("/auth/signin/{login}/{password}")
    suspend fun signIn(
        @Path("login") login: String,
        @Path("password") password: String,
    ): Response<User>

    @POST("/auth/signup/{email}/{username}/{password}")
    suspend fun signUp(
        @Path("email") email: String,
        @Path("username") username: String,
        @Path("password") password: String,
    ): Response<String>

    @POST("/auth/signout")
    suspend fun signOut(): Response<String>
}