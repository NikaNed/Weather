package com.example.weather.data.mapper

import com.example.weather.data.database.WeatherInfoDbModel
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.domain.entities.ForecastItem
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

//    fun mapDbModelToEntity(dbModel: WeatherInfoDbModel) = ForecastItem(
//        name = dbModel.name,
//        dt = convertTimestampToTime(dbModel.dt),
//        feels_like = dbModel.feels_like,
//        temp = dbModel.temp,
//        temp_max = dbModel.temp_max,
//        temp_min = dbModel.temp_min,
//        description = dbModel.description,
//        icon = dbModel.icon
//    )

//    fun mapDtoToDbModel(dto: WeatherResponse) = WeatherInfoDbModel( //преобразует класс dto в класс БД
//        name = dto.name,
//        dt = dto.dt,
//        feels_like = dto.main.feels_like,
//        temp = dto.main.temp,
//        temp_max = dto.main.temp_max,
//        temp_min = dto.main.temp_min,
//        description = dto.weather,
//        icon = BASE_URL + dto.icon
//    )


    private fun convertTimestampToTime(timestamp: Int): String {
        val stamp = Timestamp((timestamp * 1000).toLong())
        val date = Date(stamp.time)
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object{
        private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    }
}