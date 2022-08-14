package com.example.weather.domain.usecase

import com.example.weather.domain.ForecastRepository
import javax.inject.Inject

class GetForecastUseCase  @Inject constructor (
    private val repository: ForecastRepository
) {
        operator fun invoke(name: String) = repository.getForecastInfo(name)

    }
