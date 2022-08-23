package com.example.weather.data.network.modelsForecast

import com.example.weather.data.network.modelsCurrent.Main

data class ForecastListItem(
//    @SerializedName("dt")
    val dt: Int,
    var dt_txt: String, //прогнозируемое время данных в читаемом виде
    val main: Main,
    val weather: List<Weather>,
//    val wind: Wind,
//    val visibility: Int,
//    val pop: Int,
)
