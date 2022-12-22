package com.example.weather.data.network.modelsCurrent

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coord: CoordCurrentDto,
    @SerializedName("name")
    val name: String,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("main")
    val main: MainCurrentDto,
    @SerializedName("weather")
    val weather: List<WeatherCurrentDto>,
    @SerializedName("sys")
    val sys: SysCurrentDto,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("wind")
    val wind: WindCurrentDto,
    @SerializedName("clouds")
    val clouds: CloudsCurrentDto,

    )