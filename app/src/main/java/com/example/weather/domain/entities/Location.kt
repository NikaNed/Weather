package com.example.weather.domain.entities

data class Location(
    val lat: Double,
    val lon: Double,
    val country: String,
    val id: Int,
    val name: String,
)
