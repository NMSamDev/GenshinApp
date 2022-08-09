package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.WeeklyBossFragmentBinding
import com.example.carlosmendez_finalproject.model.UIState
import com.example.carlosmendez_finalproject.viewadapters.WeeklyBossAdapter

class WeeklyBossFragment: ViewModelFragment() {
    lateinit var binding: WeeklyBossFragmentBinding
    private val wBossAdapter by lazy {
        WeeklyBossAdapter(openDetails = ::openDetails)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeeklyBossFragmentBinding.inflate(layoutInflater)
        configureObserver()

        return binding.root
    }

    private fun configureObserver(){
        viewModel.weeklyBossData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.getWeeklyBosses()
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
                        wBossAdapter.setNewList(uiState.response as List<String>)
                        rvBossesList.adapter = wBossAdapter
                    }
                }
            }
        }
    }
    private fun openDetails(boosItem: String){
        viewModel.setLoadingForDetails(binding.root.id)
        findNavController().navigate(
            WeeklyBossFragmentDirections.actionNavWeeklyBossFragmentToNavWeeklyBossDetails(boosItem)
        )
    }
}