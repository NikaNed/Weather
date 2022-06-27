package com.example.weather.domain.entities

data class ForecastItem(
    val name: String,
    val dt: Long, //Время
    val lat: Double,
    val lon: Double,
    val id: Int, //Идентификатор погодных условий
    val pop: Double, //Вероятность осадков
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    val description: String,
    val icon: String,
    val speed: Double,
)
