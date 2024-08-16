package com.example.miracleplan.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideApiService(accessTokenProvider: () -> String): ApiService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(accessTokenProvider))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://jeongwoo-babo.doyun.dev")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}
