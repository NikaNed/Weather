package com.example.weather.presentation

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.weather.R
import com.example.weather.data.network.ApiFactory
import com.example.weather.data.network.modelsCurrent.WeatherResponse
import com.example.weather.databinding.FragmentCurrentWeatherBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

//    private val fragmentList = listOf(
//        DayFragment.newInstance(EXTRA_NAME_CITY),
//        WeekFragment.newInstance()
//    )

//    private val tabLayoutList = listOf(
//        "Hours",
//        "Days"
//    )

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
//        init()
        setOnClickLaunchDayFragment()

        val apiInterface = ApiFactory.apiService.getCurrentWeather("Москва")

        apiInterface.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>,
            ) {
                Log.d("TAG", "onResponse Weather Success $call ${response.body()}")

                with(binding) {
                    tvDataWithTime.text = convertTimestampToTime(response.body()?.dt ?: 0)
                    tvDescription.text = response.body()?.weather.toString()
                    tvTemperature.text = response.body()?.main?.temp?.roundToInt().toString()
                    tvTempFeel.text = response.body()?.main?.feels_like.toString()
                    Picasso.get().load(" http://openweathermap.org/img/wn/" + response.body()?.weather)
                        .into(ivWeatherIcon)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("TAG", "onResponse onFailure ${t.message}")
            }
        })
    }

    private fun convertTimestampToTime(timestamp: Int): String {
        val stamp = Timestamp(System.currentTimeMillis())
        val data = Date(stamp.time)
        val pattern = "dd.MM, HH:mm"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(data)
    }

//        viewModel = ViewModelProvider(this,
//            viewModelFactory)[CurrentWeatherViewModel::class.java] //инициализируем vM
//        viewModel.weatherItem.observe(viewLifecycleOwner) {
//            with(binding) {
//                tvDataWithTime.text = it.toString()
//                tvTemperature.text = it.toString()
//                tvTempFeel.text = it.toString()
//                tvDescription.text = it.toString()
//                Picasso.get().load(it.toString()).into(ivWeatherIcon)
//            }
//        }


//    private fun init() = with(binding) {
//        val adapter = ViewPagerAdapter(activity as FragmentActivity, fragmentList)
//        rvWeatherHours.adapter = adapter
//        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
//            tab.text = tabLayoutList[pos]
//        }
//            .attach()
//    }

    private fun setOnClickLaunchDayFragment() {
        binding.buttonHours.setOnClickListener {
            launchDayFragment()
        }
    }


    private fun launchDayFragment() {
//        val name = requireActivity().intent.getStringExtra(EXTRA_NAME_CITY) ?: EMPTY_NAME
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, DayFragment.newInstance())
            .commit()
    }

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
//        compositeDisposable.dispose()
    }

    companion object {

        private const val EXTRA_NAME_CITY = "name"
        private const val EMPTY_NAME = ""

        fun newInstance(): Fragment {
            return CurrentWeatherFragment()
        }
    }
}