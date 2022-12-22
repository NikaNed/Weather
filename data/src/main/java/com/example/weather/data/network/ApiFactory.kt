package com.example.weather.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

    private val httpLogLevel = HttpLoggingInterceptor.Level.BODY

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply { level = httpLogLevel })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val apiService: ApiService =
        retrofit.create(ApiService::class.java)
}


