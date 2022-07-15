package com.example.weather.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.data.network.ApiFactory
import com.example.weather.data.network.modelsForecast.ForecastResponse
import com.example.weather.databinding.FragmentDayBinding
import com.example.weather.presentation.adapters.WeatherAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DayFragment : Fragment() {

    private var _binding: FragmentDayBinding? = null
    private val binding: FragmentDayBinding
        get() = _binding ?: throw RuntimeException("FragmentDayBinding == null")

    private lateinit var viewModel: CurrentWeatherViewModel

    private lateinit var adapter: WeatherAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as WeatherApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiInterface = ApiFactory.apiService.getForecastWeather("Москва")

        apiInterface.enqueue(object : Callback<ForecastResponse> {

            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>,
            ) {
                Log.d("TAG", "onResponse Forecast Success $call ${response.body()?.list}")

                binding.rvWeatherHours.layoutManager = LinearLayoutManager(activity)
                adapter = WeatherAdapter()
                binding.rvWeatherHours.adapter = adapter
                adapter.submitList(response.body()?.list)

            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.d("TAG", "onResponse onFailure ${t.message}")
            }
        })
//        viewModel = ViewModelProvider(this,
//            viewModelFactory)[CurrentWeatherViewModel::class.java] //инициализируем vM
//        viewModel.weatherItem.observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//        }
    }

//    private fun initRcView() {
//
//        viewModel = ViewModelProvider(this,
//            viewModelFactory)[CurrentWeatherViewModel::class.java] //инициализируем vM
//        viewModel.weatherItem.observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//        }
//    }

    companion object {
        private const val EXTRA_NAME_CITY = "name"

        fun newInstance(): Fragment {
            return DayFragment()
//                .apply {
//                arguments = Bundle().apply {
//                    putString(EXTRA_NAME_CITY, name)
//                }
        }
    }
}



