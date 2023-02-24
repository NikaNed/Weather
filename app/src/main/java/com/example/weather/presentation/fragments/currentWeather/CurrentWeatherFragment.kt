package com.example.weather.presentation.fragments.currentWeather

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.databinding.FragmentCurrentWeatherBinding
import com.example.weather.domain.entities.state.State
import com.example.weather.presentation.*
import com.example.weather.presentation.fragments.forecast.ForecastFragment
import com.google.android.gms.location.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_current_weather.*
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt


class CurrentWeatherFragment : Fragment() {

    private lateinit var viewModel: CurrentWeatherViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var pLauncher: ActivityResultLauncher<String>

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val component by lazy {
        (requireActivity().application as WeatherApp).component
    }

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding: FragmentCurrentWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentCurrentWeatherBinding == null")

    private lateinit var connectionLiveData: ConnectionLiveData


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startConnection()
        testConnection()
        checkPermission()
        initObservers()
        getLocation()
        setOnClickLaunchDayFragment()

        toolbar.setOnClickListener {
            binding.etSearch.text.clear()
            binding.buttonHours.isEnabled = false
        }
    }

    private fun getLocation() {
        binding.icLocationToolbar.setOnClickListener {
            getLastLocation()
            binding.etSearch.text.clear()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[CurrentWeatherViewModel::class.java]

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val resultCity = binding.etSearch.text.trim().toString()
                viewModel.getCurrentInfo(resultCity)
            }
            false
        }

        viewModel.state.observe(viewLifecycleOwner) {

            binding.progressBar.visibility = View.GONE
            binding.tvNothingFound.visibility = View.GONE

            when (it) {
                is State.Progress -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is State.LoadCurrentWeather -> {
                    with(binding) {
                        clCurrentInfo?.visibility = View.VISIBLE
                        llCurrentDetails1.visibility = View.VISIBLE
                        llCurrentDetails2.visibility = View.VISIBLE
                        buttonHours.visibility = View.VISIBLE
                        buttonHours.isEnabled = true
                        tvDataWithTime.text = it.weatherInfo.dt
                        tvTemperature.text = it.weatherInfo.main.temp.roundToInt()
                            .toString() + getString(R.string.degree_celsius_metric)
                        tvTempFeel.text =
                            getString(R.string.feels_like) + it.weatherInfo.main.feels_like.roundToInt()
                                .toString() + getString(R.string.degree_metric)
                        tvDescription.text = it.weatherInfo.weather.joinToString { it.description }
                        Picasso.get()
                            .load(URL_IMAGE + it.weatherInfo.weather.joinToString { it.icon }
                                    + "@2x.png")
                            .into(ivWeatherIcon)
                        tvNameCityTitle.text =
                            it.weatherInfo.name + getString(R.string.comma) + it.weatherInfo.sys.country
                        tvHumidityValue.text =
                            it.weatherInfo.main.humidity.toString() + getString(R.string.percent_metric)
                        tvPressureValue.text = it.weatherInfo.main.pressure
                            .div(1.333).roundToInt()
                            .toString() + getString(R.string.pressure_metric)
                        tvVisibilityValue.text =
                            it.weatherInfo.visibility.div(1000)
                                .toString() + getString(R.string.mater_metric)
                        tvWindValue.text =
                            it.weatherInfo.wind.speed.toString() + getString(R.string.mc_metric)
                        tvCloudsValue.text =
                            it.weatherInfo.clouds.all.toString() + getString(R.string.percent_metric)
                    }
                }
                is State.Error -> {
                    binding.tvNothingFound.visibility = View.VISIBLE
                    binding.clCurrentInfo?.visibility = View.GONE
                }
                else -> {}
            }
        }
    }

    private fun setOnClickLaunchDayFragment() {
        binding.buttonHours.setOnClickListener {
            launchDayFragment()
            viewModel.sendCityName(binding.tvNameCityTitle.text.trim().toString())
        }
    }

    private fun launchDayFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, ForecastFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            Toast.makeText(
                requireActivity().application, "Permission is $it",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    private fun getLastLocation() {
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireActivity().application)

        if (ActivityCompat.checkSelfPermission(
                requireActivity().application,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity().application, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val mLocationRequest: LocationRequest = LocationRequest.create()
        val mLocationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    if (location != null) {
                        val lat = location.latitude
                        val lon = location.longitude
                        viewModel.getLocationByCoord(lat, lon)
                    }
                }
            }
        }
        LocationServices.getFusedLocationProviderClient(requireActivity().application)
            .requestLocationUpdates(mLocationRequest, mLocationCallback, null)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location == null) {
                    Toast.makeText(
                        requireActivity().application, getString(R.string.cannot_get_location),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val lat = location.latitude
                    val lon = location.longitude
                    viewModel.getLocationByCoord(lat, lon)
                }
            }
    }

    private fun testConnection() {
        connectionLiveData = ConnectionLiveData(requireActivity().application)
        connectionLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                with(binding) {
                    tvCheckInternetUnavailable.isInvisible = it
                    icLocationToolbar.isEnabled = it
                    buttonHours.isEnabled = it
                    etSearch.isEnabled = it
                }
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val cm =
            requireActivity().application.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return (capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET))
    }

    private fun startConnection() {
        with(binding) {
            if (isNetworkAvailable()) {
                tvCheckInternetUnavailable.visibility = View.GONE
                icLocationToolbar.isEnabled = true
                etSearch.isEnabled = true
                buttonHours.isEnabled = true
            } else {
                icLocationToolbar.isEnabled = false
                tvCheckInternetUnavailable.visibility = View.VISIBLE
                etSearch.isEnabled = false
                buttonHours.isEnabled = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val URL_IMAGE = "http://openweathermap.org/img/wn/"

        fun newInstance(): Fragment {
            return CurrentWeatherFragment()
        }
    }
}