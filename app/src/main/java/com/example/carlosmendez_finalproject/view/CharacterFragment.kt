package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carlosmendez_finalproject.databinding.CharacterFragmentBinding

class CharacterFragment: ViewModelFragment() {
    lateinit var binding: CharacterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterFragmentBinding.inflate(layoutInflater)

        return binding.root
    }
}