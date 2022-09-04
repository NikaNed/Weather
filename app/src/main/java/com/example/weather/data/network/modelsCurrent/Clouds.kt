package com.example.weather.data.network.modelsCurrent

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("dt")
    val all: Int
)