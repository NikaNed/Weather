package com.example.weather.domain.entities

data class ForecastListItem(
    val dt: Int,
    val dt_txt: String,
    val main: MainForecast,
    val weather: List<WeatherForecast>
)



