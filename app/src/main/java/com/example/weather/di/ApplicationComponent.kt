package com.example.weather.di

import android.app.Application
import com.example.weather.presentation.*
import dagger.BindsInstance
import dagger.Component



@Component(modules = [DataModule::class, ViewModelModule::class])
@ApplicationScope
interface ApplicationComponent  {

    fun inject(fragment: CurrentWeatherFragment)
    fun inject(fragment: DayFragment)

    fun inject(activity: WeatherActivity)

    fun inject(application: WeatherApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}