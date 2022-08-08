package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carlosmendez_finalproject.databinding.ArtifactFragmentBinding

class ArtifactFragment: ViewModelFragment() {
    lateinit var binding: ArtifactFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ArtifactFragmentBinding.inflate(layoutInflater)


        return binding.root
    }
}