package com.example.carlosmendez_finalproject.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.api.ValidatorRepository
import com.example.carlosmendez_finalproject.databinding.RegisterFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment: ViewModelFragment(), ValidatorRepository {
    private lateinit var binding: RegisterFragmentBinding
    private val mAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(layoutInflater)
//        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
        binding.btnRegister.setOnClickListener(){
//            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()

            if(!fieldEmpty()){
                if(validFormat()) {
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {
//                            databaseReference.child("users").child(name)
                            Toast.makeText(this.context, "Registration completed!", Toast.LENGTH_SHORT).show()
                            mAuth.currentUser!!.sendEmailVerification()
                            val intent = Intent(requireActivity(), IndexActivity()::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                    }.addOnFailureListener {
                        Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
            else {
                Toast.makeText(this.context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun validFormat(): Boolean {
        var valid = true
        val email = binding.etEmail
        val pass = binding.etPassword
        val passConfirm = binding.etPasswordConfirm
        val validEmail = validateEmail(email)
        val validPass = validatePassword(pass)
        if(!validEmail) {
//            Toast.makeText(this.context, validEmail.toString(), Toast.LENGTH_LONG).show()
            valid = false
        }

        if(!validPass){
//            Toast.makeText(this.context, validPass.toString(), Toast.LENGTH_LONG).show()
            valid = false
        }

        if(pass.text.toString() != passConfirm.text.toString()){
            passConfirm.error = "Passwords must match"
            valid = false
        }
        return valid
    }

    private fun fieldEmpty(): Boolean {
        var empty = false
        val email = binding.etEmail
        val pass = binding.etPassword
        val passConfirm = binding.etPasswordConfirm

        if(!notEmptyET(email))
                empty = true

        if(!notEmptyET(pass))
            empty = true

        if (!notEmptyET(passConfirm))
            empty = true

        return empty
    }
 }