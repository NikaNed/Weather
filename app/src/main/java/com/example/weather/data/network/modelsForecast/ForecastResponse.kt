package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("city")
    val city: CityForecastDto,
    @SerializedName("list")
    val list: List<ForecastListItemDto>,
    @SerializedName("cnt")
    val cnt: Int
)