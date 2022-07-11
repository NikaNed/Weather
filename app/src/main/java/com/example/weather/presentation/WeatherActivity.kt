package com.example.weather.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R
import com.example.weather.data.network.ApiFactory
import com.example.weather.databinding.ActivityWeatherBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWeatherBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        launchFragment()


        }


    private fun launchFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CurrentWeatherFragment.newInstance())
            .commit()
    }
}