package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    val pod: String
)