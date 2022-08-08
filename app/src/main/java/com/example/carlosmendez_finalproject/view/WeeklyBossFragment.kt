package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carlosmendez_finalproject.databinding.WeeklyBossFragmentBinding

class WeeklyBossFragment: ViewModelFragment() {
    lateinit var binding: WeeklyBossFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeeklyBossFragmentBinding.inflate(layoutInflater)


        return binding.root
    }
}