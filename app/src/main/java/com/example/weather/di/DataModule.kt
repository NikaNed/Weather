package com.example.weather.di

import com.example.weather.data.network.api.ApiFactory
import com.example.weather.data.network.api.ApiService
import com.example.weather.data.repository.ForecastRepositoryImpl
import com.example.weather.domain.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindForecastRepository(impl: ForecastRepositoryImpl): ForecastRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}