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
import com.example.weather.presentation.adapters.WeatherAdapter
import javax.inject.Inject


class DayFragment : Fragment() {

    private var _binding: FragmentDayBinding? = null
    private val binding: FragmentDayBinding
        get() = _binding ?: throw RuntimeException("FragmentDayBinding == null")

    private lateinit var viewModel: ForecastViewModel

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

        viewModel = ViewModelProvider(this,
            viewModelFactory)[ForecastViewModel::class.java] //инициализируем vM
        viewModel.forecastInfo.observe(viewLifecycleOwner) {
            adapter = WeatherAdapter()
            binding.rvWeatherHours.layoutManager = LinearLayoutManager(context)
            binding.rvWeatherHours.adapter = adapter
            adapter.submitList(it)

        }
        viewModel.getForecastInfo("Москва")


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //присваиваем значение null
    }

    companion object {
        private const val EXTRA_NAME_CITY = "name"

        fun newInstance(name: String): Fragment {
            return DayFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(EXTRA_NAME_CITY, name)
                    }
                }
        }
    }
}



