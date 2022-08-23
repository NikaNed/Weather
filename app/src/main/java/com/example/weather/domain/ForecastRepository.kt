package com.example.weather.domain

import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.ForecastResponse
import retrofit2.Call

interface ForecastRepository {

    fun getCurrentInfoList(name: String): Call<WeatherResponse>

    fun getLocationByName(): Call<ForecastResponse>

    fun getForecastInfo(name: String): Call<ForecastResponse>

//    fun addCityName(forecastItem: ForecastItem)
}
