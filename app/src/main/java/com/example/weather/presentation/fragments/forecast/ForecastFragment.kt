package com.example.weather.presentation.fragments.forecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.FragmentDayBinding
import com.example.weather.domain.entities.state.State
import com.example.weather.presentation.ViewModelFactory
import com.example.weather.presentation.WeatherApp
import com.example.weather.presentation.fragments.currentWeather.CurrentWeatherViewModel
import com.example.weather.presentation.fragments.currentWeather.adapters.WeatherAdapter
import javax.inject.Inject

class ForecastFragment : Fragment() {

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

        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[CurrentWeatherViewModel::class.java]

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewModel.cityName.observe(viewLifecycleOwner) {
            binding.toolbar.title = it
            viewModel.getForecastInfo(it)
        }

        viewModel.state.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE

            when (it) {

                is State.Progress -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is State.LoadForecastWeather -> {

                    adapter = WeatherAdapter()
                    with(binding) {
                        recycler.layoutManager = LinearLayoutManager(context)
                        recycler.adapter = adapter
                        adapter.submitList(it.forecastInfo)

                        LinearLayoutManager(
                            requireActivity().application,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    }
                }
                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): Fragment {
            return ForecastFragment()
        }
    }
}



