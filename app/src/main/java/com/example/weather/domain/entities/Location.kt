package com.example.weather.domain.entities

data class Location(
    val woeid: Int,
    val title: String,
    val locationType: String,
    val latLon: String,
)
