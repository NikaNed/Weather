package com.example.weather.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.weather.domain.entities.ForecastListItem

object WeatherInfoDiffCallback: DiffUtil.ItemCallback<com.example.weather.domain.entities.ForecastListItem>() {

    override fun areItemsTheSame(oldItem: com.example.weather.domain.entities.ForecastListItem, newItem: com.example.weather.domain.entities.ForecastListItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: com.example.weather.domain.entities.ForecastListItem, newItem: com.example.weather.domain.entities.ForecastListItem): Boolean {
        return oldItem == newItem
    }
}