package com.example.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetForecastUseCase
import com.example.weather.domain.usecase.SearchCityUseCase
import kotlinx.coroutines.*
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    private val searchCityUseCase: SearchCityUseCase
) : ViewModel() {

    private val _currentInfo = MutableLiveData<WeatherResponse>()
    val currentInfo: LiveData<WeatherResponse>
        get() = _currentInfo

    private val _forecastInfo = MutableLiveData<List<ForecastListItem>>()
    val forecastInfo: LiveData<List<ForecastListItem>>
        get() = _forecastInfo

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean>
        get() = _progressVisible

    private val _errorIncorrectCity = MutableLiveData<Boolean>()
    val errorIncorrectCity: LiveData<Boolean>
        get() = _errorIncorrectCity

    private val _currentDetail = MutableLiveData<Boolean>()
    val currentDetail: LiveData<Boolean>
        get() = _currentDetail

    var job: Job? = null

    init {
        _currentDetail.value = false
    }

    fun getCurrentInfo(nameCity: String) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = getCurrentWeatherUseCase.invoke(nameCity)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _currentDetail.value = true
                    _currentInfo.postValue(response.body())
                    _progressVisible.value = false
                } else {
                    _errorIncorrectCity.value = true
                    _progressVisible.value = false
                    _currentDetail.value = false
                    Log.d("TAG", "onResponse onFailure ${response.message()}")
                }
            }
        }
    }

    fun getCityName(nameCity: String) {
        _errorIncorrectCity.value = false
        _progressVisible.value = true
        _currentDetail.value = false
    }

    fun getForecastInfo(name: String) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = getForecastUseCase.invoke(name)
            withContext(Dispatchers.Main) {
                _progressVisible.value = true
                if (response.isSuccessful) {
                    _forecastInfo.postValue(response.body()?.list)
                    _progressVisible.value = false
                } else {
                    _errorIncorrectCity.value = true
                    _progressVisible.value = false
                    Log.d("TAG", "onResponse onFailure ${response.message()}")
                }
            }
        }
    }

    fun getLocationByCoord(lat:Double, lon:Double) {

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = searchCityUseCase.invoke(lat, lon)
            withContext(Dispatchers.Main) {
                _progressVisible.value = true
                if (response.isSuccessful) {
                    _currentDetail.value = true
                    _currentInfo.postValue(response.body())
                    _progressVisible.value = false
                    _errorIncorrectCity.value = false
                } else {
                    _errorIncorrectCity.value = true
                    _progressVisible.value = false
                    _currentDetail.value = false
                    Log.d("TAG", "onResponse onFailure ${response.message()}")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}