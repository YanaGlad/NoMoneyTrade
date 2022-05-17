package com.example.nomoneytrade.di

import com.example.nomoneytrade.api.Api
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://nomoneytrade.threadjava800.repl.co/"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        val contentType = MediaType.get("application/json")
        val json = Json { ignoreUnknownKeys = true }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpBuilder.build())
            .build()
    }

    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}
