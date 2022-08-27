package com.example.weather.data.network.modelsCurrent

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
//    val main: String
    //    val id: Int
)