package com.example.weather.data.network.modelsCurrent

import com.google.gson.annotations.SerializedName

data class CoordCurrentDto(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)