package com.example.weather.data.network

import com.example.weather.data.network.models.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast")
    suspend fun getCurrentWeather(
        @Query("q") name: String,
        @Query("lang") languageCode: String = "ru",
        @Query("units") units: String = "metric"
    ): Response<ForecastResponse>

    @GET("forecast")
    suspend fun searchCity(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric"
    ): Response<ForecastResponse>

}