package com.example.weather.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.FragmentDayBinding
import com.example.weather.presentation.adapters.WeatherAdapter
import kotlinx.android.synthetic.main.fragment_current_weather.*
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

        viewModel = ViewModelProvider(requireActivity(),
            viewModelFactory)[CurrentWeatherViewModel::class.java]

        viewModel.nameCity.observe(viewLifecycleOwner) {
            binding.toolbar.title = it
            viewModel.getForecastInfo(it)
        }

        viewModel.forecastInfo.observe(viewLifecycleOwner) {
            adapter = WeatherAdapter()
            binding.recycler.layoutManager = LinearLayoutManager(context)
            binding.recycler.adapter = adapter
            adapter.submitList(it)
        }

        viewModel.progressVisible.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EXTRA_NAME_CITY = "name"

        fun newInstance(): Fragment {
            return DayFragment()
        }
    }
}



