package com.example.weather.presentation.fragments.currentWeather.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ItemForecastDayBinding
import com.example.weather.domain.entities.ForecastListItem
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemForecastDayBinding.bind(view)

    fun bind(item: ForecastListItem) = with(binding) {

        tvDate.text = item.dt_txt
        tvDescription.text = item.weather.joinToString { it.description }
        tvTemperature.text = item.main.temp.roundToInt().toString() + "°"
        Picasso.get()
            .load(URL_IMAGE + item.weather.joinToString { it.icon } + "@2x.png")
            .into(ivWeatherIcon)
    }

    companion object {
        const val URL_IMAGE = "http://openweathermap.org/img/wn/"
    }
}
