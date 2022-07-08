package com.example.weather.di

import androidx.lifecycle.ViewModel
import com.example.weather.presentation.CurrentWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    @Binds
    fun bindCurrentWeatherViewModel(viewModel: CurrentWeatherViewModel) : ViewModel
}