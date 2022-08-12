package com.example.weather.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.network.modelsCurrent.Main
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.domain.entities.ForecastItem
import com.example.weather.domain.entities.SearchItem
import com.example.weather.domain.usecase.GetCurrentWeatherUseCase
import com.example.weather.domain.usecase.GetForecastUseCase
import com.example.weather.domain.usecase.SearchCityUseCase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    private val _nameCity = MutableLiveData<String>()
    val nameCity: LiveData<String>
        get() = _nameCity

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName



    fun getCurrentInfo(name: String) {

        val response = getCurrentWeatherUseCase.invoke(name)

        response.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>,
            ) {
                viewModelScope.launch {   _currentInfo.postValue(response.body()) }
                Log.d("TAG", "onResponse Weather Success $call ${response.body()}")

            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("TAG", "onResponse onFailure ${t.message}")
            }
        })
    }

    fun getCityName(nameCity: String) {
        val name = parseName(nameCity)
        val fieldsVailed = validateInput(name)
        if (fieldsVailed) { // если поля валидные, то добаялем новый элемент
            viewModelScope.launch {  _nameCity.value = nameCity  }
        }
    }



    private fun parseName(inputName: String?): String { //приводим строку ввода в нормальный вид
        //принимает нулабельный тип, а возвращает ненулабельную строку
        return inputName?.trim() ?: "" // если inputName не null, то обрезаем пробелы, если null,то
        // возвращаем пустую строку
    }

    private fun validateInput(name: String): Boolean { // проводим валидацию
        var result = true
        if (name.isBlank()) {
            result = false
            _errorInputName.value = true
        }
        return result
    }


//    val currentInfoList = getCurrentWeatherUseCase()
//
//    val forecastInfoList = getForecastUseCase()
}