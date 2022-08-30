package com.example.weather.di

import android.app.Application
import com.example.weather.data.database.AppDatabase
import com.example.weather.data.database.WeatherInfoDao
import com.example.weather.data.network.ApiFactory
import com.example.weather.data.network.ApiService
import com.example.weather.data.repository.ForecastRepositoryImpl
import com.example.weather.domain.ForecastRepository
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
        fun provideWeatherInfoDao(
            application: Application,
        ): WeatherInfoDao {
            return AppDatabase.getInstance(application).weatherInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

//        @Provides
//        @ApplicationScope
//        fun provideRequestInterceptor(): Response {
//            return RequestInterceptor().intercept()
//        }
    }

}