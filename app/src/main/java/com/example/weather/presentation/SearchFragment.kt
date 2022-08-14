package com.example.weather.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.databinding.FragmentSearchBinding
import com.example.weather.presentation.adapters.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding == null")

    private lateinit var viewModel: SearchViewModel

    private lateinit var adapter: SearchAdapter

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
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this,
            viewModelFactory)[SearchViewModel::class.java] //инициализируем vM
        viewModel.searchCity.observe(viewLifecycleOwner) {
            adapter = SearchAdapter()

            with(binding) {
                recycler.layoutManager = LinearLayoutManager(context)
                recycler.adapter = adapter

                etSearch.addTextChangedListener {
                    // viewModel.getCity(inputNameCity)

                    launchCurrentWeatherFragment()
                }
            }
//                toolbar.setNavigationOnClickListener { viewModel.onBackPressed() }
        }
    }

    private fun launchCurrentWeatherFragment() {

        val nameCity = etSearch.text.toString()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CurrentWeatherFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

//    fun onClickResult(view: View) {
//        if (!fieldEmpty()) {
//            val resultText = getResult()
//
//        }
//    }

        private fun fieldEmpty(): Boolean {
            binding.apply {
                if (etSearch.text.isNullOrEmpty()) etSearch.error = getString(R.string.error_empty)
                return etSearch.text.isNullOrEmpty()
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null //присваиваем значение null
        }

        companion object {
            private const val EXTRA_NAME_CITY = "name"

            fun newInstance(nameCity: String): Fragment {
                return SearchFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(EXTRA_NAME_CITY, nameCity)
                    }
                }
            }
        }
    }



