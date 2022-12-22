package com.example.weather.presentation.fragments.currentWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.entities.ForecastListItem
import com.example.weather.domain.entities.WeatherEntity
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetForecastUseCase
import com.example.weather.domain.usecase.SearchCityUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    private val searchCityUseCase: SearchCityUseCase,
) : ViewModel() {

    private val _currentInfo = MutableLiveData<WeatherEntity>()
    val currentInfo: LiveData<WeatherEntity>
        get() = _currentInfo

    private val _forecastInfo =
        MutableLiveData<List<ForecastListItem>>()
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

    init {
        _currentDetail.value = false
    }

    fun getCurrentInfo(nameCity: String) {

        viewModelScope.launch {
            _progressVisible.value = true

            withContext(Dispatchers.IO) {
                val response = getCurrentWeatherUseCase.invoke(nameCity)
                if (response == null) {
                    withContext(Dispatchers.Main) {
                        _errorIncorrectCity.value = true
                        _progressVisible.value = false
                        _currentDetail.value = false
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _currentDetail.value = true
                        _currentInfo.postValue(response)
                        _progressVisible.value = false
                    }
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

        viewModelScope.launch {
            _progressVisible.value = true

            withContext(Dispatchers.IO) {
                val response = getForecastUseCase.invoke(name)
                if (response == null) {
                    withContext(Dispatchers.Main) {
                        _progressVisible.value = false
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _forecastInfo.postValue(response.list)
                        _progressVisible.value = false
                    }
                }
            }
        }
    }

    fun getLocationByCoord(lat: Double, lon: Double) {

        viewModelScope.launch {
            _progressVisible.value = true
            withContext(Dispatchers.IO) {
                val response = searchCityUseCase.invoke(lat, lon)
                if (response == null) {
                    withContext(Dispatchers.Main) {
                        _errorIncorrectCity.value = true
                        _progressVisible.value = false
                        _currentDetail.value = false
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        _currentInfo.postValue(response)
                        _currentDetail.value = true
                        _progressVisible.value = false
                        _errorIncorrectCity.value = false
                    }
                }
            }
        }
    }
}
