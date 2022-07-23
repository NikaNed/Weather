package com.example.weather.presentation.adapters

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.databinding.ItemForecastDayBinding
import com.squareup.picasso.Picasso
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemForecastDayBinding.bind(view)

    fun bind(item: ForecastListItem) = with(binding){

        tvDate.text = item.dt_txt.substringAfter("-").substringBeforeLast(":")
        tvDescription.text = item.weather.joinToString { it.description }
        tvTemperature.text = item.main.temp.roundToInt().toString() + "С°"
        Picasso.get().load("http://openweathermap.org/img/wn/" + item.weather.joinToString { it.icon } +"@2x.png").into(ivWeatherIcon)
        Log.d("TAG"," ${convertTimestampToTime(item.dt_txt)}")
    }

    private fun convertTimestampToTime(timestamp: String): String {
        val stamp = Timestamp(System.currentTimeMillis())
        val data = Date(stamp.time)
        val pattern = "dd.MM, HH:mm"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(data)
    }
}
