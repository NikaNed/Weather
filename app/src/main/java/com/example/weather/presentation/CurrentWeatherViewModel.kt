package com.example.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetForecastUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
) : ViewModel() {

/*    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state*/


    private val _currentInfo = MutableLiveData<WeatherResponse>()
    val currentInfo: LiveData<WeatherResponse>
        get() = _currentInfo

    fun getCurrentInfo(name: String) {

        val response = getCurrentWeatherUseCase.invoke(name)

        response.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>,
            ) {
                Log.d("TAG", "onResponse Weather Success $call ${response.body()}")
                _currentInfo.postValue(response.body())
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("TAG", "onResponse onFailure ${t.message}")
            }
        })
    }

//    val currentInfoList = getCurrentWeatherUseCase()
//
//    val forecastInfoList = getForecastUseCase()
}