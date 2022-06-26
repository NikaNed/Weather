package com.example.weather.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weather.databinding.FragmentDayBinding

class DayFragment: Fragment() {

    private var _binding: FragmentDayBinding? = null
    private val binding: FragmentDayBinding
        get() = _binding ?: throw RuntimeException("FragmentDayBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDayBinding.inflate(inflater, container, false)
        return binding.root
    }
}
