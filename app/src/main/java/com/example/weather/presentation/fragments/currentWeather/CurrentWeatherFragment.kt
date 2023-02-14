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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.databinding.FragmentCurrentWeatherBinding
import com.example.weather.presentation.ViewModelFactory
import com.example.weather.presentation.WeatherApp
import com.example.weather.presentation.InternetConnection
import com.example.weather.presentation.fragments.forecast.ForecastFragment
import com.example.weather.presentation.isPermissionGranted
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_current_weather.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
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

        checkPermission()
        initObservers()
        checkPermission()
        startConnection()
        testConnection()
        getLocation()
        setOnClickLaunchDayFragment()

        toolbar.setOnClickListener {
            binding.etSearch.text.clear()
        }
    }

    private fun getLocation() {
        binding.icLocationToolbar.setOnClickListener {
            fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(requireActivity().application)
            getLastLocation()
            binding.etSearch.text.clear()
        }
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(requireActivity().application,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireActivity().application,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location == null)
                    Toast.makeText(requireActivity().application,
                        getString(R.string.cannot_get_location),
                        Toast.LENGTH_SHORT).show()
                else {
                    val lat = location.latitude
                    val lon = location.longitude
                    viewModel.getLocationByCoord(lat, lon)
                }
            }
    }

    private fun testConnection() {
        val internet = InternetConnection(requireActivity().application)
        internet.observe(viewLifecycleOwner) { isConnected ->
            with(binding) {
                if (isConnected) {
                    tvCheckInternetUnavailable.visibility = View.GONE
                    icLocationToolbar.visibility = View.VISIBLE
                    buttonHours.isEnabled = true
                } else {
                    tvCheckInternetUnavailable.visibility = View.VISIBLE
                    icLocationToolbar.visibility = View.GONE
                    binding.etSearch.isEnabled = false
                    buttonHours.isEnabled = false
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
                icLocationToolbar.visibility = View.VISIBLE
            } else {
                icLocationToolbar.visibility = View.GONE
                tvCheckInternetUnavailable.visibility = View.VISIBLE
                etSearch.isEnabled = false
                buttonHours.isEnabled = false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        viewModel = ViewModelProvider(requireActivity(),
            viewModelFactory)[CurrentWeatherViewModel::class.java]

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val resultCity = binding.etSearch.text.trim().toString()
                viewModel.getCityName(resultCity)
                viewModel.getCurrentInfo(resultCity)
            }
            false
        }

        viewModel.errorIncorrectCity.observe(viewLifecycleOwner) {
            binding.tvNothingFound.isVisible = it
        }

        viewModel.currentInfo.observe(viewLifecycleOwner) {
            with(binding) {
                buttonHours.isEnabled = true
                tvDataWithTime.text = convertTimestampToTime(it.dt)
                tvTemperature.text =
                    it.main.temp.roundToInt().toString() + getString(R.string.degree_celsius_metric)
                tvTempFeel.text =
                    getString(R.string.feels_like) + it.main.feels_like.roundToInt()
                        .toString() + getString(R.string.degree_metric)
                tvDescription.text = it.weather.joinToString { it.description }
                    .replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                        else it.toString()
                    }
                Picasso.get()
                    .load(URL_IMAGE + it.weather.joinToString { it.icon } + "@2x.png")
                    .into(ivWeatherIcon)
                tvNameCityTitle.text = it.name + getString(R.string.comma) + it.sys.country
                tvHumidityValue.text =
                    it.main.humidity.toString() + getString(R.string.percent_metric)
                tvPressureValue.text =
                    it.main.pressure.div(1.333).roundToInt()
                        .toString() + getString(R.string.pressure_metric)
                tvVisibilityValue.text =
                    it.visibility.div(1000).toString() + getString(R.string.mater_metric)
                tvWindValue.text = it.wind.speed.toString() + getString(R.string.mc_metric)
                tvCloudsValue.text = it.clouds.all.toString() + getString(R.string.percent_metric)
            }
        }

        viewModel.currentDetail.observe(viewLifecycleOwner) {
            with(binding) {
                llCurrentDetails1.isVisible = it
                llCurrentDetails2.isVisible = it
                tvCurrentDetails.isVisible = it
                tvHumidity.isVisible = it
                tvPressure.isVisible = it
                tvVisibility.isVisible = it
                tvWind.isVisible = it
                tvClouds.isVisible = it
                buttonHours.isVisible = it
                tvDataWithTime.isVisible = it
                tvTemperature.isVisible = it
                tvTempFeel.isVisible = it
                tvDescription.isVisible = it
                tvNameCityTitle.isVisible = it
                ivWeatherIcon.isVisible = it
                tvNameCityTitle.isVisible = it
            }
        }

        viewModel.progressVisible.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
    }

    private fun convertTimestampToTime(dt: Int): String {
        val stamp = Timestamp(System.currentTimeMillis())
        val data = Date(stamp.time)
        val pattern = "dd MMMM, HH:mm"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(data)
    }

    private fun setOnClickLaunchDayFragment() {
        binding.buttonHours.setOnClickListener {
            launchDayFragment()
        }
    }

    private fun launchDayFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, ForecastFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()) {
            Toast.makeText(requireActivity().application, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
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