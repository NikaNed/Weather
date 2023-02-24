package com.example.weather.domain.usecase

import com.example.weather.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val repository: ForecastRepository,
) {

    suspend operator fun invoke(cityName: String) = repository.getForecastInfo(cityName)
}
