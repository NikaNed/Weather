package com.example.weather.data.network.test

data class SimpleResponse(

    val weather: List<Weather>,
    val base: String,
    val all: Int,
    val cod: Int,
    val lat: Double,
    val lon: Double,
    val dt: Int,
    val id: Int,
    val name: String,
    val timezone: Int,
    val visibility: Int,
    val deg: Int,
    val gust: Double,
    val speed: Double,
    val country: String,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)