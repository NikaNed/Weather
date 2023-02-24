package com.example.weather.data.mapper

import com.example.weather.data.network.modelsCurrent.*
import com.example.weather.domain.entities.WeatherEntity
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun mapDtoToEntityWeather(dto: WeatherResponse) = WeatherEntity(
        coord = mapDtoToEntityCoord(dto.coord),
        name = dto.name,
        dt = convertTimestampToTime(dto.dt),
        main = mapDtoToEntityMain(dto.main),
        weather = mapDtoToEntityWeatherCurrent(dto.weather),
        sys =mapDtoToEntitySys(dto.sys),
        visibility = dto.visibility,
        wind = mapDtoToEntityWind(dto.wind),
        clouds = mapDtoToEntityClouds(dto.clouds)
    )

    private fun mapDtoToEntityCoord(dto: CoordCurrentDto) = WeatherEntity.Coord(
        lat = dto.lat,
        lon = dto.lon
    )

    private fun mapDtoToEntityMain(dto: MainCurrentDto) = WeatherEntity.Main(
        feels_like = dto.feels_like,
        temp = dto.temp,
        humidity = dto.humidity,
        pressure = dto.pressure
    )

    private fun mapDtoToEntityWeatherCurrent(dto: List<WeatherCurrentDto>):
            List<WeatherEntity.WeatherCurrent> {
        return dto.map {
            WeatherEntity.WeatherCurrent(
                description = (it.description).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                    else it.toString()
                },
                icon = it.icon
            )
        }
    }

    private fun mapDtoToEntitySys(dto:SysCurrentDto) = WeatherEntity.Sys(
        country = dto.country
    )

    private fun mapDtoToEntityWind(dto: WindCurrentDto) = WeatherEntity.Wind(
        speed = dto.speed
    )

    private fun mapDtoToEntityClouds(dto: CloudsCurrentDto) = WeatherEntity.Clouds(
        all = dto.all
    )

    private fun convertTimestampToTime(dt: Int): String {
        val stamp = Timestamp(System.currentTimeMillis())
        val data = Date(stamp.time)
        val pattern = "dd MMMM, HH:mm"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(data)
    }
}