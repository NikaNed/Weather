package com.example.weather.data.network.modelsForecast

import com.example.weather.data.network.modelsCurrent.Main
import com.google.gson.annotations.SerializedName

data class ForecastListItem(
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("dt_txt")
    var dt_txt: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("weather")
    val weather: List<Weather>,
//    val wind: Wind,
//    val visibility: Int,
//    val pop: Int,
)
