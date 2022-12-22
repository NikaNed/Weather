package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class WindForecastDto(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double,
    @SerializedName("speed")
    val speed: Double
)