package com.example.weather.domain.entities


data class WeatherEntity(
    val coord: Coord,
    val name: String,
    val dt: String,
    val main: Main,
    val weather: List<WeatherCurrent>,
    val sys: Sys,
//    val timezone: Int,
    val visibility: Int,
//    val id: Int,
    val wind: Wind,
    val clouds: Clouds,
) {

    data class Coord(
        val lat: Double,
        val lon: Double,
    )

    data class Main(
        val feels_like: Double,
        val temp: Double,
//        val temp_max: Double,
//        val temp_min: Double,
        val humidity: Int,
        val pressure: Int,
//        val grnd_level: Int,
//        val sea_level: Int,
    )

    data class WeatherCurrent(
        val description: String,
        val icon: String,
//        val main: String,
//        val id: Int,
    )

    data class Sys(
        val country: String,
//        val id: Int,
//        val sunrise: Int,
//        val sunset: Int,
//        val type: Int,
    )

    data class Wind(
        val speed: Double,
    )

    data class Clouds(
        val all: Int
    )
}
