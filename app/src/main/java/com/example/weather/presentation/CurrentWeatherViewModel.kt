package com.example.weather.presentation

import androidx.lifecycle.ViewModel
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
/*    private val getForecastForDaysUseCase: GetForecastForDaysUseCase,
    private val searchCityUseCase: SearchCityUseCase,*/
) : ViewModel() {

/*    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state*/

/*
    private val _forecastParams: MutableLiveData<ForecastItem>()
    val forecastParams: LiveData<ForecastItem>
        get() = _forecastParams

    private val _currentWeatherParams: MutableLiveData<ForecastItem>()
    val currentWeatherParams: LiveData<ForecastItem>
        get() = _currentWeatherParams*/

    val forecastItem = getCurrentWeatherUseCase()

//    fun getCurrentWeather() {
////        viewModelScope.launch {
//            val list = getCurrentWeatherUseCase.getCurrentWeather()
//            forecastItem.value = list.value // устанавлием его в LiveData
////        }
//    }
}