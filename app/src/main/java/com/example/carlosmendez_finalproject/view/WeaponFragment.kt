package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.WeaponFragmentBinding
import com.example.carlosmendez_finalproject.model.UIState
import com.example.carlosmendez_finalproject.viewadapters.WeaponAdapter
import com.example.carlosmendez_finalproject.viewmodel.GenshinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeaponFragment: ViewModelFragment() {
    lateinit var binding: WeaponFragmentBinding
    private val weaponAdapter by lazy {
        WeaponAdapter(openDetails = ::openDetails)
    }

    //private val viewModel: GenshinViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeaponFragmentBinding.inflate(layoutInflater)
        configureObserver()

        return binding.root
    }

    private fun configureObserver() {
        viewModel.weaponData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.getWeapons()
                }
                is UIState.Error -> {
                    binding.apply {
                        pbLoading.visibility = View.VISIBLE
                        tvLoading.visibility = View.VISIBLE
                        tvLoading.text = uiState.error.message
                    }

                }
                is UIState.Success<*> -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvLoading.visibility = View.GONE
                        weaponAdapter.setNewList(uiState.response as List<String>)
                        rvWeaponList.adapter = weaponAdapter
                    }
                }
            }
        }
    }

    private fun openDetails(weaponItem: String) {
        viewModel.setLoadingForDetails(binding.root.id)
        findNavController().navigate(
            WeaponFragmentDirections.actionNavWeaponFragmentToWeaponDetailFragment(weaponItem)
        )
    }

}