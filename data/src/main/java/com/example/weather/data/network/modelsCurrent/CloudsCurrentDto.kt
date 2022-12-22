package com.example.weather.data.network.modelsCurrent

import com.google.gson.annotations.SerializedName

data class CloudsCurrentDto(
    @SerializedName("dt")
    val all: Int
)