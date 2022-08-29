package com.example.weather.data.mapper

import androidx.lifecycle.LiveData
import com.example.weather.data.database.CityNameDbModel
import com.example.weather.data.database.WeatherInfoDbModel
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.City
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.domain.entities.ForecastItem
import com.example.weather.domain.entities.Location
import com.example.weather.domain.entities.SearchItem
import retrofit2.Call
import javax.inject.Inject
//
//class WeatherMapper @Inject constructor() {
//
//    fun mapEntityToDbModel(forecastItem: ForecastItem) = WeatherInfoDbModel(
//        name = forecastItem.name,
//        dt = forecastItem.dt,
//        feels_like = forecastItem.feels_like,
//        temp = forecastItem.temp,
//        temp_max = forecastItem.temp_max,
//        temp_min = forecastItem.temp_min,
//        description = forecastItem.description,
//        icon = forecastItem.icon
//    )
//
//    fun mapEntityToDbModelCity(cityNameDbModel: CityNameDbModel) =
//        SearchItem(
//            name = cityNameDbModel.name,
//        )
//
//    fun mapDbModelToEntity(cityNameDbModel: CityNameDbModel) =
//        SearchItem( //метод преобразовывает модель БД в сущность domain-слоя
//            name = cityNameDbModel.name
//        )
//
////    fun mapDbModelListToListEntity(list: List<CityNameDbModel>) = list.map {
////        mapDbModelToEntity(it) //для каждого элемента коллекции вызовем метод
////    }
//
//
////    fun mapDtoToDbModel(dto: WeatherResponse) = WeatherInfoDbModel( //преобразует класс dto в класс БД
////        name = dto.name,
////        dt = dto.dt,
////        feels_like = dto.main.feels_like,
////        temp = dto.main.temp,
////        temp_max = dto.main.temp_max,
////        temp_min = dto.main.temp_min,
////        description = dto.weather,
////        icon = BASE_URL + dto.icon
////    )
//
////    fun mapDtoToEntity(dto: Call<WeatherResponse>) =
////        Location( //метод преобразовывает модель БД в сущность domain-слоя
////            name = dto.name
////        )
//
//    fun City.toLocationVo(isInFavorites: Boolean = false) = Location(
//        name = name
//    )
//
//    fun WeatherResponse.toLocation() = Location(
//     name = name,
//
//    )
//
//
////    private fun convertTimestampToTime(timestamp: String): String {
////        val stamp = Timestamp((timestamp * 1000).toLong())
////        val date = Date(stamp.time)
////        val pattern = "yyyy-MM-dd HH:mm:ss"
////        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
////        sdf.timeZone = TimeZone.getDefault()
////        return sdf.format(date)
////    }
//
//    companion object {
//        private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
//    }
//}