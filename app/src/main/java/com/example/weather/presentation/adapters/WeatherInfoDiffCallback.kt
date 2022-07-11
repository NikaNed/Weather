package com.example.weather.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.weather.domain.entities.ForecastItem

object WeatherInfoDiffCallback: DiffUtil.ItemCallback<ForecastItem>() {

    override fun areItemsTheSame(oldItem: ForecastItem, newItem: ForecastItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ForecastItem, newItem: ForecastItem): Boolean {
        return oldItem == newItem
    }
}