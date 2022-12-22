package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class CloudsForecastDto(
    @SerializedName("all")
    val all: Int
)