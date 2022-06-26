package com.example.weather.data.network.models

data class City(
    val lat: Double,
    val lon: Double,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)