package com.example.weather.presentation

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.databinding.FragmentCurrentWeatherBinding
import com.example.weather.presentation.adapters.InternetConnection
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

    private val component by lazy {
        (requireActivity().application as WeatherApp).component
    }

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding: FragmentCurrentWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentCurrentWeatherBinding == null")


    private lateinit var cld: InternetConnection

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

        setOnClickLaunchDayFragment()

        testConnection()

//        setOnClickLaunchWeekFragment()

//        toolbar.setNavigationOnClickListener {
//            launchSearchFragment()
//        }

        toolbar.setOnClickListener {
            binding.etSearch.text.clear()
        }
    }

    private fun testConnection() {
        cld = InternetConnection(requireActivity().application)
        cld.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                Toast.makeText((requireActivity().application), "Connected", Toast.LENGTH_SHORT)
                    .show()
                binding.tvCheckInternetUnavailable.visibility = View.GONE
            } else {
                binding.tvCheckInternetAvailable.visibility = View.GONE
                binding.tvCheckInternetUnavailable.visibility = View.VISIBLE
            }
        }
    }


    private fun initObservers() {
        viewModel = ViewModelProvider(requireActivity(),
            viewModelFactory)[CurrentWeatherViewModel::class.java]

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.getCityName(binding.etSearch.text.toString())
            }
            false
        }

        viewModel.errorInputName.observe(viewLifecycleOwner) {
            etSearch.error = getString(R.string.error_enter_name)
            binding.buttonHours.isEnabled = it != true
        }

        viewModel.errorIncorrectCity.observe(viewLifecycleOwner) {
            binding.buttonHours.isEnabled = it != true
            binding.tvNothingFound.isVisible = it
            with(binding) {
                llCurrentDetails1.visibility = View.GONE
                llCurrentDetails2.visibility = View.GONE
                tvCurrentDetails.visibility = View.GONE
                tvHumidity.visibility = View.GONE
                tvPressure.visibility = View.GONE
                tvVisibility.visibility = View.GONE
                tvWind.visibility = View.GONE
                tvClouds.visibility = View.GONE
            }
        }

        viewModel.nameCity.observe(viewLifecycleOwner) {
            (binding.etSearch as TextView).text = it.toString()
            viewModel.getCurrentInfo(it.toString())
        }

        viewModel.currentInfo.observe(viewLifecycleOwner) {
            with(binding) {
                buttonHours.isEnabled = true
                tvDataWithTime.text = convertTimestampToTime(it.dt)
                tvTemperature.text = it.main.temp.roundToInt().toString() + "°С"
                tvTempFeel.text =
                    "Ощущается как " + it.main.feels_like.roundToInt().toString() + "°"
                tvDescription.text = it.weather.joinToString { it.description }
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                Picasso.get()
                    .load(URL_IMAGE + it.weather.joinToString { it.icon } + "@2x.png")
                    .into(ivWeatherIcon)
                tvNameCityTitle.text = it.name + " , " + it.sys.country
                tvHumidityValue.text = it.main.humidity.toString() + "%"
                tvPressureValue.text = it.main.pressure.toString() + "hPa"
                tvVisibilityValue.text = it.visibility.div(1000).toString() + "km"
                tvWindValue.text = it.wind.speed.toString() + "m/s"
                tvCloudsValue.text = it.clouds.all.toString() + "%"
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
            }
        }

        viewModel.resetFields.observe(viewLifecycleOwner) {
            with(binding) {
                tvDataWithTime.text = ""
                tvTemperature.text = ""
                tvTempFeel.text = ""
                tvDescription.text = ""
                tvNameCityTitle.text = ""
                Picasso.get()
                    .load(URL_IMAGE + "")
                    .into(ivWeatherIcon)
                tvNameCityTitle.text = ""
                tvHumidityValue.text = ""
                tvPressureValue.text = ""
                tvVisibilityValue.text = ""
                tvWindValue.text = ""
                tvCloudsValue.text = ""
            }
        }

        viewModel.progressVisible.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        viewModel.buttonForecast.observe(viewLifecycleOwner) {
            binding.buttonHours.isVisible = it
        }
    }


    private fun convertTimestampToTime(dt: Int): String {
        val stamp = Timestamp(System.currentTimeMillis())
        val data = Date(stamp.time)
        val pattern = "MMMM dd, HH:mm"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(data)
    }

    private fun setOnClickLaunchDayFragment() {
        binding.buttonHours.setOnClickListener {
            viewModel.getCityName(binding.etSearch.text.toString())
            launchDayFragment()
        }
    }


    private fun launchDayFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, DayFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

//    private fun setOnClickLaunchWeekFragment() {
//        binding.buttonDays.setOnClickListener {
////            viewModel.getCityName(binding.etSearch.text.toString())
//            launchSearchFragment()
//        }
//    }

//    private fun launchSearchFragment() {
//        val nameCity = requireActivity().intent.getStringExtra(EXTRA_NAME_CITY) ?: EMPTY_NAME
//        requireActivity().supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragment_container, SearchFragment.newInstance(nameCity))
//            .addToBackStack(null)
//            .commit()
//    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()) {
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val URL_IMAGE = "http://openweathermap.org/img/wn/"
        const val EXTRA_NAME_CITY = "name"
        const val EMPTY_NAME = ""

        fun newInstance(): Fragment {
            return CurrentWeatherFragment()
        }
    }
}