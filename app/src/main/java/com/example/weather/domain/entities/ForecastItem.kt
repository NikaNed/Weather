package com.example.weather.domain.entities

data class ForecastItem(
    val name: String,
    val dt: Long, //Время
    val feels_like: Double,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    val description: String,
    val icon: String,
)
