package com.example.weather.data.repository

import com.example.weather.data.database.WeatherInfoDao
import com.example.weather.data.mapper.WeatherMapper
import com.example.weather.data.network.ApiFactory
import com.example.weather.data.network.ApiService
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.domain.ForecastRepository
import retrofit2.Call
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
//    private val application: Application,
    private val mapper: WeatherMapper, //инжектим mapper
    private val weatherInfoDao: WeatherInfoDao,
    private val apiService: ApiService
) : ForecastRepository {

    override fun getCurrentInfoList(name: String): Call<WeatherResponse>{

        return apiService.getCurrentWeather(name)
//        return Transformations.map(weatherInfoDao.getCurrentList()) {
//            it.map {
//                mapper.mapDbModelToEntity(it)
//            }
//        }
    return TODO("Not yet implemented")
    }

    override fun getForecastInfo(name: String): Call<ForecastResponse> {
        return apiService.getForecastWeather(name)

//        return Transformations.map(weatherInfoDao.getForecastList()) {
//            it.map {
//                mapper.mapDbModelToEntity(it)
//            }
//        }

    }

    /* override fun searchCity(name: String): LiveData<List<Location>> {
         TODO("Not yet implemented")
     }




        fun getForecastForWeek(): LiveData<List<ForecastItem>> {
            TODO("Not yet implemented")
        }*/


}
