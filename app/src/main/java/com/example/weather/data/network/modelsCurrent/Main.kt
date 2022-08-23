package com.example.weather.data.network.modelsCurrent

data class Main(
    val feels_like: Double,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    val humidity: Int,
    val pressure: Int,
//    val grnd_level: Int,
//    val sea_level: Int,
)