package com.example.weather.presentation.fragments.currentWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.entities.state.State
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetForecastUseCase
import com.example.weather.domain.usecase.SearchCityUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    private val searchCityUseCase: SearchCityUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String>
        get() = _cityName


    fun sendCityName(name: String) {
        _cityName.value = name
    }

    fun getCurrentInfo(nameCity: String) {

        viewModelScope.launch {

            _state.value = State.Progress
            val response = getCurrentWeatherUseCase.invoke(nameCity)
            if (response == null) {
                _state.value = State.Error
            } else {
                _state.value = State.LoadCurrentWeather(response)
            }
        }
    }

    fun getForecastInfo(cityName: String) {

        viewModelScope.launch {
            _state.value = State.Progress
            val response = getForecastUseCase.invoke(cityName)
            if (response == null) {
                _state.value = State.Error
            } else {
                _state.value = State.LoadForecastWeather(response.list)
            }
        }
    }

    fun getLocationByCoord(lat: Double, lon: Double) {

        viewModelScope.launch {
            _state.value = State.Progress
            val response = searchCityUseCase.invoke(lat, lon)
            if (response == null) {
                _state.value = State.Error
            } else {
                _state.value = State.LoadCurrentWeather(response)
            }
        }
    }
}
