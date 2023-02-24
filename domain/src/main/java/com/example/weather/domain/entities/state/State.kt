package com.example.weather.domain.entities.state

import com.example.weather.domain.entities.ForecastListItem
import com.example.weather.domain.entities.WeatherEntity

sealed class State{

    class LoadCurrentWeather(val weatherInfo: WeatherEntity): State()
    class LoadForecastWeather(val forecastInfo: List<ForecastListItem>): State()
    object Progress: State()
    object Error: State()
}
