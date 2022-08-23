package com.example.weather.data.repository

import com.example.weather.data.database.WeatherInfoDao
import com.example.weather.data.mapper.WeatherMapper
import com.example.weather.data.network.ApiService
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.domain.ForecastRepository
import com.example.weather.domain.entities.Location
import retrofit2.Call
import javax.inject.Inject
class ForecastRepositoryImpl @Inject constructor(
//    private val application: Application,
    private val mapper: WeatherMapper, //инжектим mapper
    private val apiService: ApiService,
    private val weatherInfoDao: WeatherInfoDao
) : ForecastRepository {



    override fun getCurrentInfoList(name: String): Call<WeatherResponse> {
        return apiService.getCurrentWeather(name)
    }

    override fun getForecastInfo(name: String): Call<ForecastResponse> {
        return apiService.getForecastWeather(name)
    }

    override fun getLocationByName(): Call<ForecastResponse>  {
        return apiService.getLocationByName()
    }


//    override fun addCityName(forecastItem: ForecastItem) {
//        weatherInfoDao.searchCity(mapper.mapEntityToDbModel(forecastItem))
//    }

//    override fun searchCity(forecastItem: ForecastItem) {
//        weatherInfoDao.searchCity(mapper.mapEntityToDbModel(forecastItem))
//    }
}
