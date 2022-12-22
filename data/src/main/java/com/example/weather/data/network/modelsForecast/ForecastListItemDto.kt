package com.example.weather.data.network.modelsForecast

import com.google.gson.annotations.SerializedName

data class
ForecastListItemDto(
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("dt_txt")
    val dt_txt: String,
    @SerializedName("main")
    val main: MainForecastDto,
    @SerializedName("weather")
    val weather: List<WeatherForecastDto>,
    @SerializedName("wind")
    val wind: WindForecastDto,
    @SerializedName("visibility")
    val visibility: Int,
)
