package com.example.carlosmendez_finalproject.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.api.ValidatorRepository
import com.example.carlosmendez_finalproject.databinding.LoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment: ViewModelFragment(), ValidatorRepository {
    private lateinit var binding: LoginFragmentBinding
    private val mAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(layoutInflater)
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
//            System.exit(200)
//        }
        binding.apply {
            btnRegister.setOnClickListener(){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
            btnLogin.setOnClickListener() {
                val email = binding.etEmail
                val pass = binding.etPassword
                if(fieldNotEmpty()){
                    mAuth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString()).addOnSuccessListener {
                        val intent = Intent(requireActivity(), IndexActivity()::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }.addOnFailureListener {
                        Toast.makeText(this@LoginFragment.context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
//            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMenuFragment())
            }
        }

        return binding.root
    }

    private fun fieldNotEmpty():Boolean {
        var result = true
        if(!notEmptyET(binding.etEmail))
            result = false
        if(!notEmptyET(binding.etPassword))
            result = false
        return result
    }
}