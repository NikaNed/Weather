package com.example.weather.presentation

import android.R
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.network.modelsForecast.City
import com.example.weather.databinding.FragmentSearchBinding
import com.example.weather.presentation.adapters.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*
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

        val cityList = ArrayList<String>()
        cityList.add("London")
        cityList.add("Miami")
        cityList.add("California")
        cityList.add("Los Angeles")
        cityList.add("Chicago")
        cityList.add("Houston")


        val cityAdapter = ArrayAdapter(requireActivity().application,
            R.layout.simple_spinner_dropdown_item,
            cityList)
        binding.autocomplete.setAdapter(cityAdapter)


        binding.autocomplete.setOnItemClickListener { adapterView, view, position, l ->
            val city = adapterView.getItemAtPosition(position).toString()
            binding.autocomplete.setText(city)
            closeKeyBoard()
        }


//            val cityAdapter = SearchAdapter(
//                requireActivity().application,
//                R.layout.simple_spinner_dropdown_item,
//                cityList
//            )
//
//            binding.autocomplete.setAdapter(cityAdapter)
//            binding.autocomplete.setOnItemClickListener { _, _, position, _ ->
//                val city = cityAdapter.getItem(position) as City?
//                binding.autocomplete.setText(city?.name)
//            }

//        viewModel.searchCity.observe(viewLifecycleOwner) {
//            adapter = SearchAdapter()
//
//            with(binding) {
//                recycler.layoutManager = LinearLayoutManager(context)
//                recycler.adapter = adapter
////                adapter.submitList(it)
//
////               etSearch.setOnEditorActionListener { view, actionId, event ->
////                    if (actionId == EditorInfo.IME_ACTION_DONE) {
////                        viewModel.getCityName((view as EditText).text.toString())
////                    }
////                    false
////                }
//
//                autocomplete.onItemClickListener =
//                    AdapterView.OnItemClickListener { parent, _, position, _ ->
//                        val place = parent.getItemAtPosition(position) as City
//                        autocomplete.setText(place.name)
//                    }
//            }


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

        fun newInstance(nameCity: String): Fragment {
            return SearchFragment()
//                    .apply {
//                        arguments = Bundle().apply {
//                            putString(EXTRA_NAME_CITY, nameCity)
//                        }
//                    }
        }

        val COUNTRIES = arrayOf(
            "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
            "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
            "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
            "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
            "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
            "Bolivia Marching Powder Confederated Anarchic Socialist Communes and Rest Homes")
    }
}



