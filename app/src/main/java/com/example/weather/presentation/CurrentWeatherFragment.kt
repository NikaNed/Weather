package com.example.weather.presentation

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.databinding.FragmentCurrentWeatherBinding
import com.example.weather.presentation.adapters.WeatherAdapter
import com.squareup.picasso.Picasso
import javax.inject.Inject

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

        viewModel = ViewModelProvider(this,
            viewModelFactory)[CurrentWeatherViewModel::class.java] //инициализируем vM
        viewModel.weatherItem.observe(viewLifecycleOwner) {
            with(binding) {
                tvDataWithTime.text = it.toString()
                tvTemperature.text = it.toString()
                tvTempFeel.text = it.toString()
                tvDescription.text = it.toString()
                Picasso.get().load(it.toString()).into(ivWeatherIcon)
            }
        }

    }

//


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