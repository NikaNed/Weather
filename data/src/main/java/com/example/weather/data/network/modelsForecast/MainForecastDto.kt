package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class MainForecastDto(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_max")
    val temp_max: Double,
    @SerializedName("temp_min")
    val temp_min: Double,
    @SerializedName("grnd_level")
    val grnd_level: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("feels_like")
    val feels_like: Double,
)