package com.example.weather.domain

import androidx.lifecycle.LiveData
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.City
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.domain.entities.ForecastItem
import com.example.weather.domain.entities.Location
import com.example.weather.domain.entities.SearchItem
import retrofit2.Call

interface ForecastRepository {

    fun getCurrentInfoList(name: String): Call<WeatherResponse>

    fun getLocationByName(inputName: String): List<Location>

    fun getForecastInfo(name: String): Call<ForecastResponse>

//    fun addCityName(forecastItem: ForecastItem)
}
