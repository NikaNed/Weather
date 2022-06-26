package com.example.weather.data.network.models

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ConsolidatedForecast>,
    val message: Int
)