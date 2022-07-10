package com.example.weather.di

import android.app.Application
import com.example.weather.presentation.CurrentWeatherFragment
import com.example.weather.presentation.DayFragment
import com.example.weather.presentation.WeatherApp
import dagger.BindsInstance
import dagger.Component


/*@ApplicationScope*/
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent  {

    fun inject(fragment: CurrentWeatherFragment)
    fun inject(fragment: DayFragment)

//    fun inject(activity: WeatherActivity)

    fun inject(application: WeatherApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}