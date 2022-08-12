package com.example.weather.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.domain.entities.ForecastItem
import com.example.weather.domain.entities.SearchItem

@Dao
interface WeatherInfoDao {

    @Query("SELECT * FROM weather_items ORDER BY dt")
    fun getCurrentList(): LiveData<List<WeatherInfoDbModel>>

    @Query("SELECT * FROM weather_items ORDER BY dt")
    fun getForecastList(): LiveData<List<WeatherInfoDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherList(weatherList: List<WeatherInfoDbModel>)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun searchCity(weatherInfoDbModel: WeatherInfoDbModel)

    @Query("SELECT * FROM city_items")
    fun getCityName(): LiveData<CityNameDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun searchCityName(cityNameDbModel: CityNameDbModel)
}
