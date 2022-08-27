package com.example.weather.data.network.modelsCurrent

import com.google.gson.annotations.SerializedName

data class Main(
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
//    val grnd_level: Int,
//    val sea_level: Int,
)