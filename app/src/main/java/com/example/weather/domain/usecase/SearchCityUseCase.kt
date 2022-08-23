package com.example.weather.domain.usecase

import com.example.weather.domain.ForecastRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor (

    private val repository: ForecastRepository
) {

    operator fun invoke() = repository.getLocationByName()

}