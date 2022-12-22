package com.example.weather.data.network.modelsCurrent

import com.google.gson.annotations.SerializedName

data class MainCurrentDto(
    @SerializedName("feels_like")
    val feels_like: Double,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_max")
    val temp_max: Double,
    @SerializedName("temp_min")
    val temp_min: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("grnd_level")
    val grnd_level: Int,
    @SerializedName("sea_level")
    val sea_level: Int,
)