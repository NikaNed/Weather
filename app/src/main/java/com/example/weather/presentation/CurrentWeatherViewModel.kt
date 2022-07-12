package com.example.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.ListenableWorker
import com.example.weather.data.database.WeatherInfoDao
import com.example.weather.data.mapper.WeatherMapper
import com.example.weather.data.network.ApiFactory
import com.example.weather.data.network.ApiFactory.apiService
import com.example.weather.data.network.modelsCurrent.Coord
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.domain.entities.ForecastItem
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
/*    private val getForecastForDaysUseCase: GetForecastForDaysUseCase,
    private val searchCityUseCase: SearchCityUseCase,*/
) : ViewModel() {

/*    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state*/

    private val compositeDisposable = CompositeDisposable()

    private val _weatherItem = MutableLiveData<List<ForecastItem>>()
    val weatherItem: LiveData<List<ForecastItem>>
        get() = _weatherItem


//    val weatherItem = getCurrentWeatherUseCase()

    fun getCurrentWeather() {
//        viewModelScope.launch {
        val item = getCurrentWeatherUseCase.invoke()
        _weatherItem.value = item.value // устанавлием его в LiveData
//        }
    }

    init {
        load()
    }

    fun load() {

                val topCoin =
                    apiService.getCurrentWeather() //загрузили топ 50 популярных валют
//

        }
    }


//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.dispose()
//    }
