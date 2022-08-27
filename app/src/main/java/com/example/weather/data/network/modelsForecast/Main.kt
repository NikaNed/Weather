package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_max")
    val temp_max: Double,
    @SerializedName("temp_min")
    val temp_min: Double
//    val grnd_level: Int,
//    val humidity: Int,
//    val pressure: Int,
//    val feels_like: Double
)