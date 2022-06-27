package com.example.weather.data.network.models

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ForecastResponse(

/*    @SerializedName("NAME")
    @Expose*/
    val name: String,
    val dt: Long, //Время
    val lat: Double,
    val lon: Double,
    val id: Int, //Идентификатор погодных условий
    val pop: Double, //Вероятность осадков
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    val description: String,
    val icon: String,
    val speed: Double,
    val hours: String
)