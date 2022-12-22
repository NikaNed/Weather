package com.example.weather.domain

import com.example.weather.domain.entities.ForecastEntity
import com.example.weather.domain.entities.WeatherEntity

interface ForecastRepository {

    suspend fun getCurrentInfoList(name: String): WeatherEntity?

    suspend fun getForecastInfo(name: String): ForecastEntity?

    suspend fun getLocation(lat: Double, lon: Double): WeatherEntity?

}
