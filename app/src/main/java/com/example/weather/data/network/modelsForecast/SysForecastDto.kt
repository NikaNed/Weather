package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class SysForecastDto(
    @SerializedName("pod")
    val pod: String
)