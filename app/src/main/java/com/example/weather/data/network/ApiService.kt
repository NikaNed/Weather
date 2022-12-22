package com.example.weather.data.network

import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") name: String,
        @Query("units") units: String = "metric",
    ): WeatherResponse

    @GET("forecast")
    suspend fun getForecastWeather(
        @Query("q") name: String,
        @Query("units") units: String = "metric",
    ): ForecastResponse

    @GET("weather")
    suspend fun getLocationByCoord(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
    ): WeatherResponse
}