package com.example.nomoneytrade.api

import com.example.nomoneytrade.api.dto.AllPostResponse
import com.example.nomoneytrade.api.dto.ProductDto
import com.example.nomoneytrade.api.requests.SignInBody
import com.example.nomoneytrade.api.requests.SignUpBody
import com.example.nomoneytrade.api.responses.BaseResponse
import com.example.nomoneytrade.api.dto.UserDto
import com.example.nomoneytrade.api.requests.ProductRequest
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @Headers("Content-Type: application/json")
    @POST("/auth/signin")
    suspend fun signIn(
        @Body signInBody: SignInBody,
    ): Response<UserDto>

    @Multipart
    @POST("/auth/signup")
    suspend fun signUp(
        @Part("user_data") sighUpBody: SignUpBody,
        @Part file: MultipartBody.Part?,
    ): Response<UserDto>

    @POST("/auth/signout")
    suspend fun signOut(): Response<BaseResponse>

    @Multipart
    @POST("/api/new_post")
    suspend fun newProduct(
        @Part("post_content") productRequest: ProductRequest,
        @Part file: MultipartBody.Part?
   ): Response<BaseResponse>

    @POST("/api/get_posts")
    suspend fun getAllProducts(): Response<AllPostResponse>
}