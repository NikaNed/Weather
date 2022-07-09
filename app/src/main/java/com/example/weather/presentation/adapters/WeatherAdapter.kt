package com.example.weather.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.weather.R
import com.example.weather.databinding.ItemForecastDayBinding
import com.example.weather.domain.entities.ForecastItem
import com.example.weather.presentation.CurrentWeatherFragment
import com.squareup.picasso.Picasso

class WeatherAdapter(private val context: CurrentWeatherFragment) :
    ListAdapter<ForecastItem, WeatherViewHolder>(WeatherInfoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemForecastDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherViewHolder(binding)
    }


    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            with(item) {
                val dataTemplate = context.resources.getString(R.string.data_template)
                tvDate.text = String.format(dataTemplate, dt)
                tvDescription.text = description
                tvTemperature.text = temp.toString()
                Picasso.get().load(icon).into(ivWeatherIcon)
            }
        }
    }
}

