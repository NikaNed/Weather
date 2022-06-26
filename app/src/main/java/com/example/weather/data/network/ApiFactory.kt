package com.example.weather.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

    private val retrofit = Retrofit.Builder() //создаем объект retrofit
        .addConverterFactory(GsonConverterFactory.create())

        .baseUrl(BASE_URL) //указываем к какому базовому URL будем образаться
        .build()

    val apiService = retrofit.create(ApiService::class.java) //для генирации кода описанного api
// предоставляем класс интерфейса
}