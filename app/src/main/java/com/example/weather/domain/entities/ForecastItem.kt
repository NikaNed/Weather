package com.example.weather.domain.entities

data class ForecastItem(
    val id: Long,
    val payload: Any?,
    val temp: String,
    val tempMin: String,
    val date: String,
    val weatherType: String,
    val humidity: String,
    val pressure: String,
    val description: String,
    val windSpeed: String
)
