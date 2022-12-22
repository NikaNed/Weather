package com.example.weather.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weather.R
import com.example.weather.domain.entities.ForecastListItem

class WeatherAdapter: ListAdapter<com.example.weather.domain.entities.ForecastListItem, WeatherViewHolder>(WeatherInfoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_forecast_day,
            parent,
            false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}



