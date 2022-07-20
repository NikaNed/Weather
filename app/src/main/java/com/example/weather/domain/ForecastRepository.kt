package com.example.weather.domain

import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.ForecastResponse
import retrofit2.Call

interface ForecastRepository {

    fun getCurrentInfoList(name: String): Call<WeatherResponse>

//    fun searchCity(name: String): LiveData<List<Location>>
//
    fun getForecastInfo(name: String): Call<ForecastResponse>


}
