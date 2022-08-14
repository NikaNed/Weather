package com.example.weather.presentation

import android.app.Application
import com.example.weather.di.DaggerApplicationComponent


class WeatherApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}