package com.example.weather.domain

import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.ForecastResponse
import retrofit2.Call
import retrofit2.Response

interface ForecastRepository {

    suspend fun getCurrentInfoList(name: String): Response<WeatherResponse>

    suspend fun getForecastInfo(name: String): Response<ForecastResponse>

    suspend fun getLocation(lat: Double, lon: Double): Response<WeatherResponse>

}
