package com.example.weather.data.network.modelsCurrent

data class WeatherResponse(
    val coord: Coord,
    val name: String,
    val dt: Int,
    val main: Main,
    val weather: List<Weather>,
//    val timezone: Int,
//    val visibility: Int,
    val id: Int, //идентификатор города
//    val wind: Wind
//    val clouds: Clouds,
)