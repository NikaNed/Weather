package com.example.weather.data.mapper

import com.example.weather.data.database.WeatherInfoDbModel
import com.example.weather.domain.entities.ForecastItem
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun mapDbModelToEntity(dbModel: WeatherInfoDbModel) = ForecastItem(
        name = dbModel.name,
        dt = convertTimestampToTime(dbModel.dt),
        feels_like = dbModel.feels_like,
        temp = dbModel.temp,
        temp_max = dbModel.temp_max,
        temp_min = dbModel.temp_min,
        description = dbModel.description,
        icon = dbModel.icon
    )

    private fun convertTimestampToTime(timestamp: Int): String {
        if (timestamp == null) return ""
        val stamp = Timestamp((timestamp * 1000).toLong())
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }
}