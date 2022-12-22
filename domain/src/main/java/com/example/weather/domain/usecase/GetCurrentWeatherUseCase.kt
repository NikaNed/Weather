package com.example.weather.domain.usecase

import com.example.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor (
    private val repository: ForecastRepository
) {

    suspend operator fun invoke(nameCity: String) = repository.getCurrentInfoList(nameCity)
}