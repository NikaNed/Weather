package com.example.weather.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.databinding.ItemForecastDayBinding
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class WeatherViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemForecastDayBinding.bind(view)

    fun bind(item: ForecastListItem) = with(binding){

        tvDate.text = item.dt_txt
        tvDescription.text = item.weather.toString()
        tvTemperature.text = item.main.temp.roundToInt().toString()
//        Picasso.get().load(" http://openweathermap.org/img/wn/" + item.weather[1]).into(ivWeatherIcon)
    }
}