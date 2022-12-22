package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class CoordForecastDto(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)