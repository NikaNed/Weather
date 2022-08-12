package com.example.weather.domain.entities


data class ForecastItem(
    val name: String,
    val dt: String = "",
    val feels_like: Double = 0.0,
    val temp: Double= 0.0,
    val temp_max: Double= 0.0,
    val temp_min: Double= 0.0,
    val description: String = "",
    val icon: String =""
)
