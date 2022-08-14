package com.example.weather.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "city_items")
data class CityNameDbModel(
    @PrimaryKey
    val name: String = "London"
)
