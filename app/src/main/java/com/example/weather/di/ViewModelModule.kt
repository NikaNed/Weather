package com.example.weather.di

import androidx.lifecycle.ViewModel
import com.example.weather.presentation.CurrentWeatherViewModel
import com.example.weather.presentation.ForecastViewModel
import com.example.weather.presentation.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    @Binds
    fun bindCurrentWeatherViewModel(viewModel: CurrentWeatherViewModel) : ViewModel

    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    @Binds
    fun bindForecastViewModel(viewModel: ForecastViewModel) : ViewModel

    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    @Binds
    fun bindSearchViewModel(viewModel: SearchViewModel) : ViewModel
}