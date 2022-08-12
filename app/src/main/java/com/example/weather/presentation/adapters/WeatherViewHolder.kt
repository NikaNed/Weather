package com.example.weather.presentation.adapters

import android.os.Build
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.databinding.ItemForecastDayBinding
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemForecastDayBinding.bind(view)

    fun bind(item: ForecastListItem) = with(binding) {

        tvDate.text = convertPattern(item.dt_txt).toString()
        tvDescription.text = item.weather.joinToString { it.description }
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        tvTemperature.text = item.main.temp.roundToInt().toString() + "°"
        Picasso.get()
            .load("http://openweathermap.org/img/wn/" + item.weather.joinToString { it.icon } + "@2x.png")
            .into(ivWeatherIcon)

    }

    private fun convertPattern(dt: String): String? {
        val firstApiFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val date = LocalDateTime.parse(dt, firstApiFormat)
        return date.format(DateTimeFormatter.ofPattern("d.MM HH:mm"))
    }
}
