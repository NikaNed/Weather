package com.example.weather.domain.repository

import com.example.weather.domain.entities.ForecastEntity
import com.example.weather.domain.entities.WeatherEntity

interface ForecastRepository {

    suspend fun getCurrentInfoList(name: String): WeatherEntity?

    suspend fun getForecastInfo(cityName: String): ForecastEntity?

    suspend fun getLocation(lat: Double, lon: Double): WeatherEntity?

}
