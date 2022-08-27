package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("city")
    val city: City,
    @SerializedName("list")
    val list: List<ForecastListItem>,
    @SerializedName("cnt")
    val cnt: Int
)