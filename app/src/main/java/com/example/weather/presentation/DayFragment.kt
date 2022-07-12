package com.example.weather.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.FragmentDayBinding
import com.example.weather.domain.entities.ForecastItem
import com.example.weather.presentation.adapters.WeatherAdapter
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
        initRcView()
    }

    private fun initRcView() {
        with(binding) {
            rvWeatherHours.layoutManager = LinearLayoutManager(activity)
            adapter = WeatherAdapter()
            rvWeatherHours.adapter = adapter
        }
        viewModel = ViewModelProvider(this,
            viewModelFactory)[CurrentWeatherViewModel::class.java] //инициализируем vM
        viewModel.weatherItem.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

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

