package com.example.weather.domain.usecase

import com.example.weather.domain.ForecastRepository

class GetCurrentWeatherUseCase  (
    private val repository: ForecastRepository
) {
    operator fun invoke(  ) = repository.getCurrentWeather()

}