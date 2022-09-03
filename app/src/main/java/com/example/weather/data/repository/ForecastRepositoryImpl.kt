package com.example.weather.data.repository

import com.example.weather.data.network.ApiService
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.domain.ForecastRepository
import retrofit2.Response
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ForecastRepository {

    override suspend fun getCurrentInfoList(nameCity: String): Response<WeatherResponse> {
        return apiService.getCurrentWeather(nameCity)
    }

    override suspend fun getForecastInfo(name: String): Response<ForecastResponse> {
        return apiService.getForecastWeather(name)
    }

    override suspend fun getLocation(lat: Double, lon: Double): Response<WeatherResponse> {
        return apiService.getLocationByCoord(lat, lon)
    }
}
