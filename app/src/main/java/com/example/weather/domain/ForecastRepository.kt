package com.example.weather.domain

import androidx.lifecycle.LiveData
import com.example.weather.domain.entities.ForecastItem
import com.example.weather.domain.entities.Location

interface ForecastRepository {

    fun getForecastForDay(): LiveData<List<ForecastItem>>

    fun getForecastForWeek(): LiveData<List<ForecastItem>>

    fun getLocationByName(name: String): LiveData<List<Location>>

}
