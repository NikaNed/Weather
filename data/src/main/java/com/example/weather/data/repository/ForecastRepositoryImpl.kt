package com.example.weather.data.repository

import android.util.Log
import com.example.weather.data.mapper.ForecastMapper
import com.example.weather.data.mapper.WeatherMapper
import com.example.weather.data.network.ApiService
import com.example.weather.domain.ForecastRepository
import com.example.weather.domain.entities.ForecastEntity
import com.example.weather.domain.entities.WeatherEntity
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapperWeather: WeatherMapper,
    private val mapperForecast: ForecastMapper
) : ForecastRepository {

    override suspend fun getCurrentInfoList(name: String): WeatherEntity? {

        return try {
            val responseCurrent = apiService.getCurrentWeather(name)
            mapperWeather.mapDtoToEntityWeather(responseCurrent)
        } catch (e: Exception) {
            Log.d("TAG", e.message!!)
            null
        }
    }

    override suspend fun getForecastInfo(name: String): ForecastEntity? {
        return try {
            val responseForecast = apiService.getForecastWeather(name)
            mapperForecast.mapDtoToEntityForecastResponse(responseForecast)
        } catch (e: Exception) {
            Log.d("TAG", e.message!!)
            null
        }
    }

    override suspend fun getLocation(lat: Double, lon: Double): WeatherEntity? {
        return try {
            val responseLocation = apiService.getLocationByCoord(lat, lon)
            mapperWeather.mapDtoToEntityWeather(responseLocation)
        } catch (e: Exception) {
            Log.d("TAG", e.message!!)
            null
        }
    }
}
