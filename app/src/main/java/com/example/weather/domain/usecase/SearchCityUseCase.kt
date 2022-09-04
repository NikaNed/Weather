package com.example.weather.domain.usecase

import com.example.weather.domain.ForecastRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor (

    private val repository: ForecastRepository
) {

    suspend operator fun invoke(lat: Double, lon: Double) = repository.getLocation(lat,lon)
}