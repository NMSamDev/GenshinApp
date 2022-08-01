package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.LoginFragmentBinding

class LoginFragment: ViewModelFragment() {
    lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(layoutInflater)

        binding.btnRegister.setOnClickListener(){
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }

        binding.btnLogin.setOnClickListener() {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToMenuFragment()
            )
        }
        return binding.root
    }
}