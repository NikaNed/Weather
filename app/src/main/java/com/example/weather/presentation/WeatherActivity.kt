package com.example.weather.presentation

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R
import com.example.weather.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWeatherBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
        launchFragment()
        }
    }

    private fun launchFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CurrentWeatherFragment.newInstance())
            .commit()
    }
}