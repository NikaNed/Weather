package com.example.weather.data.network.modelsForecast

data class ForecastResponse(
    val city: City,
    val list: List<ForecastListItem>,
    val cnt: Int
)