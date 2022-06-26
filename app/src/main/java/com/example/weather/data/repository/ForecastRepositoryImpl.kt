package com.example.weather.data.repository

import androidx.lifecycle.LiveData
import com.example.weather.data.network.ApiService
import com.example.weather.domain.ForecastRepository
import com.example.weather.domain.entities.ForecastItem
import com.example.weather.domain.entities.Location

/*
class ForecastRepositoryImpl (
    private val weatherApi: ApiService,
    */
/*private val weatherDb: AppDatabase*//*

) : ForecastRepository {

    override fun getCurrentWeather(): LiveData<List<ForecastItem>> {
        weatherApi.getCurrentWeather(name = String()).toLocationInformation()
    }

    override fun searchCity(name: String): LiveData<List<Location>> {
        TODO("Not yet implemented")
    }

    */
/*override fun getForecastForDay(): LiveData<List<ForecastItem>> {

    }

    override fun getForecastForWeek(): LiveData<List<ForecastItem>> {
*//*

    }


}*/
