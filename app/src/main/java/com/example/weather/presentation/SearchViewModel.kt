package com.example.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.network.modelsForecast.City
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.domain.entities.SearchItem
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetForecastUseCase
import com.example.weather.domain.usecase.SearchCityUseCase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    private val searchCityUseCase: SearchCityUseCase,
) : ViewModel() {

/*    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state*/


    private val _searchCity = MutableLiveData<SearchItem>()
    val searchCity: LiveData<SearchItem>
        get() = _searchCity



//    private val _progressVisible = MutableLiveData<Boolean>()
//    val progressVisible: LiveData<Boolean>
//    get() = _progressVisible

//    fun getCity(inputNameCity: String?) {
//
//        inputNameCity?.let { searchCityUseCase.invoke(it) }
//
//    }

}
