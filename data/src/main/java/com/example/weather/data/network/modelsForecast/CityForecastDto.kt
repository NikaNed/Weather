package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class CityForecastDto(
    @SerializedName("coord")
    val coord: CoordForecastDto,
    @SerializedName("country")
    val country: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("id")
    val id: Int,
)