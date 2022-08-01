package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.MenuFragmentBinding

class MenuFragment: ViewModelFragment() {
    lateinit var binding: MenuFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuFragmentBinding.inflate(layoutInflater)


        binding.btnCharacters.setOnClickListener(){
            viewModel.setLoading()

        }

        return binding.root
    }
}