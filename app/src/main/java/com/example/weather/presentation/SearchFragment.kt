package com.example.weather.presentation

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.data.network.modelsForecast.City
import com.example.weather.databinding.FragmentSearchBinding
import com.example.weather.presentation.adapters.SearchAdapter
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlinx.android.synthetic.main.fragment_current_weather.*
import kotlinx.android.synthetic.main.fragment_day.*
import javax.inject.Inject


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding == null")

    private lateinit var viewModel: CurrentWeatherViewModel

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
            viewModelFactory)[CurrentWeatherViewModel::class.java]

//        viewModel.searchCity.observe(viewLifecycleOwner) {
//
//             binding.tvNothingFound.isVisible = it.isEmpty()
//             binding.autocomplete.text = it.
//
//         }
//
//             val cityList = mutableListOf(
//                 City("London"),
//                 City("Miami"),
//                 City("California"),
//                 City("Los Angeles"),
//                 City("Chicago"),
//                 City("Houston")
//             )
//        with(binding){
//             context?.let { context ->
//                 val cityAdapter =
//                     SearchAdapter(context, R.layout.item_search, cityList)
//                 autocomplete.setAdapter(cityAdapter)
//                 autocomplete.setOnItemClickListener { _, _, position, _ ->
//                     val city = cityAdapter.getItem(position)
//                     autocomplete.setText(city.name)
//                     closeKeyBoard()
//                 }
//             }
//
//             viewModel.searchCity.observe(viewLifecycleOwner) {
//                 adapter = SearchAdapter()
//
//                 recycler.layoutManager = LinearLayoutManager(context)
//                 recycler.adapter = adapter
//                 adapter.submitList(it)
//
//                 etSearch.setOnEditorActionListener { view, actionId, event ->
//                     if (actionId == EditorInfo.IME_ACTION_DONE) {
//                         viewModel.getCityName((view as EditText).text.toString())
//                     }
//                     false
//                 }
//             }
//         }
//

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

//        private fun launchCurrentWeatherFragment() {
//            requireActivity().supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.fragment_container, CurrentWeatherFragment.newInstance())
//                .addToBackStack(null)
//                .commit()
//        }

//    private fun fieldEmpty(): Boolean {
//        binding.apply {
//            if (etSearch.text.isNullOrEmpty()) etSearch.error = getString(R.string.error_empty)
//            return etSearch.text.isNullOrEmpty()
//        }
//    }

    }

    private fun closeKeyBoard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null //присваиваем значение null
    }

    companion object {
        private const val EXTRA_NAME_CITY = "name"
        private const val API_KEY_GOOGLE_MAP = "AIzaSyBQh0yNbuZJcJH-HugK26MNBOxudXhBVt0"


        fun newInstance(nameCity: String): Fragment {
            return SearchFragment()
//                    .apply {
//                        arguments = Bundle().apply {
//                            putString(EXTRA_NAME_CITY, nameCity)
//                        }
//                    }
        }
    }
}



