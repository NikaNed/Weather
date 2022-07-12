package com.example.weather.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ItemForecastDayBinding
import com.example.weather.domain.entities.ForecastItem
import com.squareup.picasso.Picasso

class WeatherViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemForecastDayBinding.bind(view)

    fun bind(item: ForecastItem) = with(binding){

        tvDate.text = item.dt
        tvDescription.text = item.description
        tvTemperature.text = item.temp.toString()
//        Picasso.get().load(item.icon).into(ivWeatherIcon)
    }
}