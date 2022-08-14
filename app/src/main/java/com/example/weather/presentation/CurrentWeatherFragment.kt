package com.example.weather.presentation

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.databinding.FragmentCurrentWeatherBinding
import com.example.weather.domain.entities.Location
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

        toolbar.setNavigationOnClickListener {
//            launchSearchFragment()
        }
    }

    private fun initObservers() {
        viewModel = ViewModelProvider(requireActivity(),
            viewModelFactory)[CurrentWeatherViewModel::class.java] //инициализируем vM

        binding.etSearch.setOnEditorActionListener { view, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.getCityName((view as EditText).text.toString())
            }
            false
        }

        viewModel.nameCity.observe(viewLifecycleOwner) {
            val city = (binding.etSearch as TextView).text.toString()
            viewModel.getCurrentInfo(city)
        }

        viewModel.currentInfo.observe(viewLifecycleOwner) {
            with(binding) {
                val maxMinTemp =
                    "Day ${it.main.temp_max.roundToInt()}°↑ • Night ${it.main.temp_min.roundToInt()}°↓"
                tvDataWithTime.text = convertTimestampToTime(it.dt)
                tvTemperature.text = it.main.temp.roundToInt().toString() + "°С"
                tvMaxMinTemp.text = maxMinTemp
                tvTempFeel.text =
                    "Ощущается как " + it.main.feels_like.roundToInt().toString() + "°"
                tvDescription.text = it.weather.joinToString { it.description }
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                Picasso.get()
                    .load("http://openweathermap.org/img/wn/" + it.weather.joinToString { it.icon } + "@2x.png")
                    .into(ivWeatherIcon)
            }
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
        _binding = null //присваиваем значение null
    }

    companion object {

        const val EXTRA_NAME_CITY = "name"
        const val EMPTY_NAME = ""

        fun newInstance(): Fragment {
            return CurrentWeatherFragment()
        }
    }
}