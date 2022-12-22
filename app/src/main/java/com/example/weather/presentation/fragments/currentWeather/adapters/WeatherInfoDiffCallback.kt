package com.example.weather.presentation.fragments.currentWeather.adapters

import androidx.recyclerview.widget.DiffUtil

object WeatherInfoDiffCallback: DiffUtil.ItemCallback<com.example.weather.domain.entities.ForecastListItem>() {

    override fun areItemsTheSame(oldItem: com.example.weather.domain.entities.ForecastListItem, newItem: com.example.weather.domain.entities.ForecastListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: com.example.weather.domain.entities.ForecastListItem, newItem: com.example.weather.domain.entities.ForecastListItem): Boolean {
        return oldItem == newItem
    }
}