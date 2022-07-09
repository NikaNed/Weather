package com.example.weather.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.data.network.modelsForecast.ForecastResponse

@Dao
interface WeatherInfoDao {

    @Query("SELECT * FROM weather_items ORDER BY dt")
    fun getWeatherList(): LiveData<List<WeatherInfoDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherList(weatherList: List<WeatherInfoDbModel>)
}