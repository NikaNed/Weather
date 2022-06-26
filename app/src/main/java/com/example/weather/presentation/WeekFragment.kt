package com.example.weather.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weather.databinding.FragmentWeekBinding

class WeekFragment: Fragment() {

    private var _binding: FragmentWeekBinding? = null
    private val binding: FragmentWeekBinding
        get() = _binding ?: throw RuntimeException("FragmentWeekBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeekBinding.inflate(inflater, container, false)
        return binding.root
    }
}
