package com.example.carlosmendez_finalproject.view

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.api.ValidatorRepository
import com.example.carlosmendez_finalproject.databinding.LoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth

const val KEY_MAIL = "mail"
const val KEY_PASSWORD = "password"
const val KEY_REMEMBER = "remember"

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
        val sharedPreferences = requireActivity().getSharedPreferences("UserLogin", MODE_PRIVATE)

        binding.apply {
            etEmail.setText(sharedPreferences.getString(KEY_MAIL, ""))

            btnRegister.setOnClickListener(){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }

            btnLogin.setOnClickListener() {

                if(fieldNotEmpty()){
                    mAuth.signInWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString()).addOnSuccessListener {
                        if(cbRemember.isChecked) {
                            sharedPreferences.edit().apply {
                                putString(KEY_MAIL, etEmail.text.toString()).apply()
                                putString(KEY_PASSWORD, etPassword.text.toString()).apply()
                                putBoolean(KEY_REMEMBER, true).apply()
                            }
                        }
                        else
                            sharedPreferences.edit().clear().apply()
                        val intent = Intent(requireActivity(), IndexActivity()::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }.addOnFailureListener {
                        Toast.makeText(this@LoginFragment.context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
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