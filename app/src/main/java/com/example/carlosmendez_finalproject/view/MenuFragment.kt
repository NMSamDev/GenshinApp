package com.example.carlosmendez_finalproject.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.MenuFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class MenuFragment: ViewModelFragment() {
    lateinit var binding: MenuFragmentBinding
    private val mAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuFragmentBinding.inflate(layoutInflater)
        verifyCurrentUser()
        binding.apply {
            binding.tvUser.text = mAuth.currentUser!!.email
            btnLogout.setOnClickListener(){
                signOut()
            }
            btnCharacters.setOnClickListener(){
                viewModel.setLoading()
                findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToCharacterFragment()
                )
            }
        }

        return binding.root
    }

    private fun verifyCurrentUser() {
        if(mAuth.currentUser == null) {
            signOut()
        }
    }

    private fun signOut() {
        mAuth.signOut()
        val intent = Intent(requireActivity(), MainActivity()::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}