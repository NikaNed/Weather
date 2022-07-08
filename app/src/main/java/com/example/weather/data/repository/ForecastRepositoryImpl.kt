package com.example.weather.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.weather.domain.ForecastRepository
import com.example.weather.domain.entities.ForecastItem
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val application: Application,
//    private val mapper: CoinMapper, //инжектим mapper
) : ForecastRepository {

    override fun getCurrentWeather(): LiveData<List<ForecastItem>> {
        TODO("Not yet implemented")
    }

   /* override fun searchCity(name: String): LiveData<List<Location>> {
        TODO("Not yet implemented")
    }

    override fun getForecastForDays(): LiveData<List<ForecastItem>> {
        TODO("Not yet implemented")
    }


       fun getForecastForWeek(): LiveData<List<ForecastItem>> {
           TODO("Not yet implemented")
       }*/


}
