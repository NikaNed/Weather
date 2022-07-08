package com.example.weather.di

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
//    @ApplicationScope
    fun bindCoinRepository(impl: ForecastRepositoryImpl) : ForecastRepository

    companion object{

//        @Provides
//        @ApplicationScope
//        fun provideCoinInfoDao(
//            application: Application
//        ): CoinInfoDao {
//            return AppDatabase.getInstance(application).coinPriceInfoDao()
//        }

        @Provides
//        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}