package com.example.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.City
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetForecastUseCase
import com.example.weather.domain.usecase.SearchCityUseCase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    private val searchCityUseCase: SearchCityUseCase,
) : ViewModel() {

/*    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state*/


    private val _currentInfo = MutableLiveData<WeatherResponse>()
    val currentInfo: LiveData<WeatherResponse>
        get() = _currentInfo

    private val _forecastInfo = MutableLiveData<List<ForecastListItem>>()
    val forecastInfo: LiveData<List<ForecastListItem>>
        get() = _forecastInfo

    private val _nameCity = MutableLiveData<String>()
    val nameCity: LiveData<String>
        get() = _nameCity

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean>
        get() = _progressVisible

    private val _searchCity = MutableLiveData<List<City>>()
    val searchCity: MutableLiveData<List<City>>
        get() = _searchCity

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorIncorrectCity = MutableLiveData<Boolean>()
    val errorIncorrectCity: LiveData<Boolean>
        get() = _errorIncorrectCity

    private val _resetFields = MutableLiveData<Boolean>()
    val resetFields: LiveData<Boolean>
        get() = _resetFields

    private val _currentDetail = MutableLiveData<Boolean>()
    val  currentDetail: LiveData<Boolean>
        get() =  _currentDetail

    init {
        _currentDetail.value = false
    }

    fun getCurrentInfo(name: String) {

        val response = getCurrentWeatherUseCase.invoke(name)

        response.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>,
            ) {
                if (response.body() != null) {
                    _currentDetail.value = true
                    _currentInfo.postValue(response.body())
                    _errorIncorrectCity.postValue(false)

                    Log.d("TAG", "onResponse Weather Success $call ${response.body()}")
                } else {
                    _errorIncorrectCity.postValue(true)
                    _resetFields.value = true
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("TAG", "onResponse onFailure ${t.message}")
//                _errorIncorrectCity.postValue(true)

            }
        })
    }


    fun getCityName(nameCity: String) {
        _progressVisible.value = true
//        val name = parseName(nameCity)
        val fieldsVailed = validateInput(nameCity)
        if (fieldsVailed) {
            viewModelScope.launch {
                _nameCity.value = nameCity
                _progressVisible.value = false
            }
        }
    }

//    private fun parseName(inputName: String?): String {
//        return inputName?.trim() ?: "" // если inputName не null, то обрезаем пробелы, если null,то
//        // возвращаем пустую строку
//    }

    private fun validateInput(name: String): Boolean { // проводим валидацию
        var result = true
        if (name.isBlank()) {
            result = false
            _errorInputName.value = true
            _progressVisible.value = false

        }
        return result
    }


    fun getForecastInfo(name: String) {

        val response = getForecastUseCase.invoke(name)

        response.enqueue(object : Callback<ForecastResponse> {

            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>,
            ) {
                viewModelScope.launch {
                    _progressVisible.value = true
                    Log.d("TAG", "onResponse Forecast Success $call ${response.body()?.list}")
                    _forecastInfo.postValue(response.body()?.list)
                    _progressVisible.value = false
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.d("TAG", "onResponse onFailure ${t.message}")

            }
        })
    }
}