package com.example.weather.domain.usecase

import com.example.weather.domain.ForecastRepository

class GetForecastForDayUseCase (
    private val repository: ForecastRepository
    ) {
        operator fun invoke(  ) = repository.getForecastForDay()

    }