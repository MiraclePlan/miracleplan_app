package com.example.miracleplan.data.di

import com.example.miracleplan.data.network.ApiService
import com.example.miracleplan.data.network.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private var accessToken: String? = null

    fun setAccessToken(token: String?) {
        accessToken = token
    }

    private val authInterceptor = AuthInterceptor {
        accessToken ?: ""
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
