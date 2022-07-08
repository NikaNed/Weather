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
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.network.ApiFactory
import com.example.weather.databinding.FragmentCurrentWeatherBinding
import com.example.weather.presentation.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrentWeatherFragment : Fragment() {

    private lateinit var viewModel: CurrentWeatherViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var pLauncher: ActivityResultLauncher<String>

    private val fragmentList = listOf(
        DayFragment.newInstance(),
        WeekFragment.newInstance()
    )

    private val tabLayoutList = listOf(
        "Hours",
        "Days"
    )

    private val component by lazy {
        (requireActivity().application as WeatherApp).component
    }

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding: FragmentCurrentWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentCurrentWeatherBinding == null")

    private val compositeDisposable = CompositeDisposable()


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
        init()

        viewModel = ViewModelProvider(this,
            viewModelFactory)[CurrentWeatherViewModel::class.java] //инициализируем vM
        viewModel.forecastItem.observe(viewLifecycleOwner) {
            Log.d("TAG", it.toString())
            /*     with(binding) {
                     tvPrice.text = it.price
                 }*/
        }

        val disposable = ApiFactory.apiService.getCurrentWeather(
            "Москва",
            "cf6776e097a42e7104c009431a5c9ef8",
            "ru",
            "metric")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Load_test", it.toString())
            }, {
                Log.d("Load_test", it.message.toString())
            })
        compositeDisposable.add(disposable)

    }


    private fun init() = with(binding) {
        val adapter = ViewPagerAdapter(activity as FragmentActivity, fragmentList)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tab.text = tabLayoutList[pos]
        }
            .attach()
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {

        fun newInstance(): Fragment {
            return CurrentWeatherFragment()
        }
    }
}