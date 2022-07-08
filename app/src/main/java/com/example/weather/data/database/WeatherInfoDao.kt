package com.example.weather.data.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.data.network.modelsForecast.ForecastResponse

interface WeatherInfoDao {

    @Query("SELECT * FROM weather_items ORDER BY dt DESC")
    fun getWeatherList(): LiveData<List<ForecastListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherList(weatherList: List<ForecastResponse>)
}