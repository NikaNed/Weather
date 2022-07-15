package com.example.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetForecastUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
//    private val searchCityUseCase: SearchCityUseCase,
) : ViewModel() {

/*    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state*/

    private val compositeDisposable = CompositeDisposable()

    private val _weatherItem = MutableLiveData<List<ForecastListItem>>()
    val weatherItem: LiveData<List<ForecastListItem>>
        get() = _weatherItem


//    val weatherItem = getCurrentWeatherUseCase()

    fun getCurrentWeather() {
//        viewModelScope.launch {
        val item = getCurrentWeatherUseCase.invoke()
        _weatherItem.value// устанавлием его в LiveData
//        }
    }


//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.dispose()
//    }
}