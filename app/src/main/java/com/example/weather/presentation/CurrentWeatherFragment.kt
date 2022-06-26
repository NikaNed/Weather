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
import com.example.weather.databinding.FragmentCurrentWeatherBinding

class CurrentWeatherFragment : Fragment() {

    private lateinit var pLauncher: ActivityResultLauncher<String>

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding: FragmentCurrentWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentCurrentWeatherBinding == null")

    override fun onAttach(context: Context) {
        /* component.inject(this)*/
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

    companion object {

        fun newInstance(): Fragment {
            return CurrentWeatherFragment()/*.apply {
                arguments = Bundle().apply {
                    putString()
                }
            }*/
        }
    }
}