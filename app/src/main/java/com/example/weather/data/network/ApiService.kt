package com.example.weather.data.network

import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.City
import com.example.weather.data.network.modelsForecast.ForecastResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") name: String,
        @Query("lang") languageCode: String = "ru",
        @Query("units") units: String = "metric",
    ): Response<WeatherResponse>

    @GET("forecast")
    suspend fun getForecastWeather(
        @Query("q") name: String,
        @Query("lang") languageCode: String = "ru",
        @Query("units") units: String = "metric",
    ): Response<ForecastResponse>


    @GET("weather")
    suspend fun getLocationByCoord(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") languageCode: String = "ru",
        @Query("units") units: String = "metric",
    ): Response<WeatherResponse>
}