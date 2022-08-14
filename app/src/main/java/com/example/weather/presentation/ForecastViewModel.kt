package com.example.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetForecastUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
) : ViewModel() {

/*    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state*/


    private val _forecastInfo = MutableLiveData<List<ForecastListItem>>()
    val forecastInfo: LiveData<List<ForecastListItem>>
        get() = _forecastInfo


    fun getForecastInfo(name: String){

        val response = getForecastUseCase.invoke(name)

//        val apiInterface = ApiFactory.apiService.getForecastWeather("Москва")

       response.enqueue(object : Callback<ForecastResponse> {

            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>,
            ) {
                Log.d("TAG", "onResponse Forecast Success $call ${response.body()?.list}")
                _forecastInfo.postValue(response.body()?.list)
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.d("TAG", "onResponse onFailure ${t.message}")
            }
        })
    }

//    val currentInfoList = getCurrentWeatherUseCase()
//
//    val forecastInfoList = getForecastUseCase()
}