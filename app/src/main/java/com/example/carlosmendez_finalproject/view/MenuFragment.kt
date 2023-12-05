package com.example.carlosmendez_finalproject.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.MenuFragmentBinding
import com.example.carlosmendez_finalproject.viewmodel.GenshinViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment: Fragment() {
    lateinit var binding: MenuFragmentBinding
    private val mAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val viewModel: GenshinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuFragmentBinding.inflate(layoutInflater)
        verifyCurrentUser()

        if(viewModel!=null)
            Log.d("MENU", "onCreateView: YEH")
        else
            Log.d("MENU", "onCreateView: NULL")
        binding.apply {
            tvUser.text = mAuth.currentUser?.email

            btnLogout.setOnClickListener(){
                signOut()
            }
            btnCharacters.setOnClickListener(){
                viewModel.setLoading(btnCharacters.id)
                findNavController().navigate(
                    MenuFragmentDirections.actionNavMenuFragmentToCharacterFragment()
                )
            }

            btnWeapons.setOnClickListener {
                viewModel.setLoading(btnWeapons.id)
                findNavController().navigate(
                    MenuFragmentDirections.actionNavMenuFragmentToWeaponFragment()
                )
            }

            btnArtifacts.setOnClickListener {
                viewModel.setLoading(btnArtifacts.id)
                findNavController().navigate(
                    MenuFragmentDirections.actionNavMenuFragmentToArtifactFragment()
                )
            }

            btnBosses.setOnClickListener {
                viewModel.setLoading(btnBosses.id)
                findNavController().navigate(
                    MenuFragmentDirections.actionNavMenuFragmentToWeeklyBossFragment()
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