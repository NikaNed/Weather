package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class City(
//    val coord: Coord,
//    @SerializedName("country")
//    val country: String,
    @SerializedName("name")
    val name: String,
//    val population: Int,
//    val sunrise: Int,
//    val sunset: Int,
//    val timezone: Int,
//    @SerializedName("id")
//    val id: Int,
)