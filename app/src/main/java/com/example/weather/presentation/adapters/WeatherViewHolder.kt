package com.example.weather.presentation.adapters

import android.os.Build
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.network.modelsForecast.ForecastListItem
import com.example.weather.databinding.ItemForecastDayBinding
import com.squareup.picasso.Picasso
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME
import java.util.*
import kotlin.math.roundToInt

class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemForecastDayBinding.bind(view)

    fun bind(item: ForecastListItem) = with(binding) {

        tvDate.text = convertPattern(item.dt_txt).toString()
//            .substringAfter("-").substringBeforeLast(":")
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
        val date = LocalDateTime.parse(dt,firstApiFormat)
             return date.format(DateTimeFormatter.ofPattern( "d.MM HH:mm" ))
        Log.d("parseTesting", date.format(DateTimeFormatter.ofPattern( "d.MM HH:mm" )))
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
