package com.example.nomoneytrade.api

import com.example.nomoneytrade.api.dto.AllPostResponse
import com.example.nomoneytrade.api.dto.ProductDto
import com.example.nomoneytrade.api.requests.SignInBody
import com.example.nomoneytrade.api.requests.SignUpBody
import com.example.nomoneytrade.api.responses.BaseResponse
import com.example.nomoneytrade.api.dto.UserDto
import com.example.nomoneytrade.api.requests.GetOffersRequest
import com.example.nomoneytrade.api.requests.MakeOfferRequest
import com.example.nomoneytrade.api.requests.ProductRequest
import com.example.nomoneytrade.api.responses.AllOfferResponse
import com.example.nomoneytrade.api.responses.UserResponse
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
        @Part file: MultipartBody.Part?,
    ): Response<BaseResponse>

    @POST("/api/get_posts")
    suspend fun getAllProducts(): Response<AllPostResponse>

    @POST("/offers/get_to_me_offers")
    suspend fun getToMeOffers(@Body getOffersRequest: GetOffersRequest): Response<AllOfferResponse>

    @POST("/offers/get_my_offers")
    suspend fun getMyOffers(@Body getOffersRequest: GetOffersRequest): Response<AllOfferResponse>

    @POST("/api/get_user_by_id")
    suspend fun getUserById(@Body userId: UserId): Response<UserResponse>

    @Multipart
    @POST("/offers/new_offer")
    suspend fun newOffer(
        @Body makeOfferRequest: MakeOfferRequest,
    ): Response<BaseResponse>

}

class UserId(val userId: Long)