package com.example.weather.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "weather_items")
data class WeatherInfoDbModel(
    @PrimaryKey
    val name: String,
    val dt: Int,
    val dt_txt: String,
    val feels_like: Double,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    val description: String,
    val icon: String,
)
