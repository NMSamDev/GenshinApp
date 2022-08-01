package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.CurrentUserVerificationBinding

class CurrentUserVerification: ViewModelFragment() {
    lateinit var binding: CurrentUserVerificationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CurrentUserVerificationBinding.inflate(layoutInflater)

        val verified = false

        if (verified){
            findNavController().navigate(
                CurrentUserVerificationDirections.actionCurrentUserVerificationToMenuFragment()
            )
        }
        else
            findNavController().navigate(
                CurrentUserVerificationDirections.actionCurrentUserVerificationToLoginFragment()
            )

        return binding.root
    }


}