package com.example.weather.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.domain.entities.ForecastItem

object WeatherInfoDiffCallback: DiffUtil.ItemCallback<ForecastListItem>() {

    override fun areItemsTheSame(oldItem: ForecastListItem, newItem: ForecastListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ForecastListItem, newItem: ForecastListItem): Boolean {
        return oldItem == newItem
    }
}