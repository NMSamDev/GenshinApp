package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carlosmendez_finalproject.databinding.RegisterFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment: ViewModelFragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()

        return binding.root
    }
}