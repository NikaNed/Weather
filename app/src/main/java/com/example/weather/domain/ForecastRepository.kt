package com.example.weather.domain

import androidx.lifecycle.LiveData
import com.example.weather.domain.entities.ForecastItem
import com.example.weather.domain.entities.Location

interface ForecastRepository {

    fun getCurrentWeather(): LiveData<List<ForecastItem>>

    fun searchCity(name: String): LiveData<List<Location>>

/*    fun getForecastForDay(): LiveData<List<ForecastItem>>

    fun getForecastForWeek(): LiveData<List<ForecastItem>>*/



}
