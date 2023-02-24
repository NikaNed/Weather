package com.example.weather.data.mapper

import android.os.Build
import com.example.weather.data.network.modelsForecast.*
import com.example.weather.domain.entities.ForecastEntity
import com.example.weather.domain.entities.ForecastListItem
import com.example.weather.domain.entities.MainForecast
import com.example.weather.domain.entities.WeatherForecast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class ForecastMapper @Inject constructor() {

    fun mapDtoToEntityForecastResponse(dto: ForecastResponse) = ForecastEntity(
        list = mapDtoToEntityForecastListItem(dto.list)
    )

    private fun mapDtoToEntityForecastListItem(dto: List<ForecastListItemDto>):
            List<ForecastListItem> {
        return dto.map {
            ForecastListItem(
                dt = it.dt,
                dt_txt = convertPattern(it.dt_txt).toString(),
                main = mapDtoToEntityMain(it.main),
                weather = mapDtoToEntityForecastWeather(it.weather),
            )
        }
    }

    private fun mapDtoToEntityMain(dto: MainForecastDto) = MainForecast(
        temp = dto.temp
    )

    private fun mapDtoToEntityForecastWeather(dto: List<WeatherForecastDto>):
            List<WeatherForecast> {
        return dto.map {
            WeatherForecast(
                description = it.description.replaceFirstChar { it ->
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                },
                icon = it.icon
            )
        }
    }

    private fun convertPattern(dt: String): String? {
        val firstApiFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val date = LocalDateTime.parse(dt, firstApiFormat)
        return date.format(DateTimeFormatter.ofPattern("d.MM HH:mm"))
    }
}