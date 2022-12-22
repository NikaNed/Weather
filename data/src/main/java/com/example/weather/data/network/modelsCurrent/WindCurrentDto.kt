package com.example.weather.data.network.modelsCurrent

import com.google.gson.annotations.SerializedName

data class WindCurrentDto(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double,
    @SerializedName("speed")
    val speed: Double
)