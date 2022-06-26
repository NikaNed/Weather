package com.example.weather.data.network

import com.example.weather.data.network.models.City
import com.example.weather.data.network.models.ForecastResponse
import com.example.weather.data.network.test.SimpleResponse
import com.example.weather.data.network.test.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {



    @GET("weather")
    fun getCurrentWeather(@Query("name") name: String): Response<Weather>

    @GET("forecast")
   fun searchCity(@Query("name") name: String): Response<SimpleResponse>


    /*   @GET("forecast")
   suspend fun getForecast(@Query("name") name: String): Response<ForecastResponse>*/

}