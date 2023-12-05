package com.example.carlosmendez_finalproject.view

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.CurrentUserVerificationBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentUserVerification: Fragment() {
    private lateinit var binding: CurrentUserVerificationBinding
    private val mAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CurrentUserVerificationBinding.inflate(layoutInflater)
        val sharedPreferences = requireActivity().getSharedPreferences("UserLogin", MODE_PRIVATE)

        if (mAuth.currentUser!=null){
            val intent = Intent(requireActivity(), IndexActivity()::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        else{
            if(sharedPreferences.getBoolean(KEY_REMEMBER, false)){
                findNavController().navigate(
                    CurrentUserVerificationDirections.actionCurrentUserVerificationToLoginBiometricFragment()
                )
            }
            else{
                findNavController().navigate(
                    CurrentUserVerificationDirections.actionCurrentUserVerificationToLoginFragment()
                )
            }
        }


        return binding.root
    }


}