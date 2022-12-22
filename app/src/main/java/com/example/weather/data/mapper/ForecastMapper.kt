package com.example.weather.data.mapper

import com.example.weather.data.network.modelsForecast.*
import com.example.weather.domain.entities.ForecastEntity
import com.example.weather.domain.entities.ForecastListItem
import javax.inject.Inject

class ForecastMapper @Inject constructor() {

    fun mapDtoToEntityForecastResponse(dto: ForecastResponse) = ForecastEntity(
        list = mapDtoToEntityForecastListItem(dto.list)
    )

    private fun mapDtoToEntityForecastListItem(dto: List<ForecastListItemDto>):
            List<ForecastListItem>{
        return dto.map {
            ForecastListItem(
                dt = it.dt,
                dt_txt = it.dt_txt,
                main = mapDtoToEntityMain(it.main),
                weather = mapDtoToEntityForecastWeather(it.weather),
            )
        }
    }

    private fun mapDtoToEntityMain(dto: MainForecastDto) = ForecastListItem.MainForecast(
        temp = dto.temp
    )

    private fun mapDtoToEntityForecastWeather(dto: List<WeatherForecastDto>):
            List<ForecastListItem.WeatherForecast>{
        return dto.map {
            ForecastListItem.WeatherForecast(
                description = it.description,
                icon = it.icon
            )
        }
    }
}