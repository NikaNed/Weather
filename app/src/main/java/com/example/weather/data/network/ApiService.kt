package com.example.weather.data.network

import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.City
import com.example.weather.data.network.modelsForecast.ForecastResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") name: String = "",
        @Query("appid") appid: String = API_KEY,
        @Query("lang") languageCode: String = "ru",
        @Query("units") units: String = "metric",
    ): Call<WeatherResponse>

    @GET("forecast")
    fun getForecastWeather(
        @Query("q") name: String = "",
        @Query("appid") appid: String = API_KEY,
        @Query("lang") languageCode: String = "ru",
        @Query("units") units: String = "metric",
    ): Call<ForecastResponse>


//    @GET("forecast")
//    fun searchCity(
//        @Query("appid") appid: String = API_KEY,
//        @Query("lat") lat: Double,
//        @Query("lon") lon: Double,
//        @Query("units") units: String = "metric"
//    ): Single<City>

    companion object {
        const val API_KEY = "cf6776e097a42e7104c009431a5c9ef8"

    }
}