package com.example.weather.domain.usecase

import com.example.weather.domain.ForecastRepository

class SearchCityUseCase(

    private val repository: ForecastRepository
) {

    operator fun invoke(name: String) = repository.searchCity(name)
}