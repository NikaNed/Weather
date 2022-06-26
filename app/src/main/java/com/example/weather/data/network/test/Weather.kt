package com.example.weather.data.network.test

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val name: String,
    val main: String,
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)