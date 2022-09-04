package com.example.weather.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.weather.data.network.modelsForecast.ForecastListItem

object WeatherInfoDiffCallback: DiffUtil.ItemCallback<ForecastListItem>() {

    override fun areItemsTheSame(oldItem: ForecastListItem, newItem: ForecastListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ForecastListItem, newItem: ForecastListItem): Boolean {
        return oldItem == newItem
    }
}