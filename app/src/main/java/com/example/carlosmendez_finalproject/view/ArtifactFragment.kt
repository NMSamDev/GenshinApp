package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.ArtifactFragmentBinding
import com.example.carlosmendez_finalproject.model.UIState
import com.example.carlosmendez_finalproject.viewadapters.ArtifactAdapter

class ArtifactFragment: ViewModelFragment() {
    lateinit var binding: ArtifactFragmentBinding
    private val artifactAdapter by lazy {
        ArtifactAdapter(openDetails = :: openDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ArtifactFragmentBinding.inflate(layoutInflater)
        configureObserver()

        return binding.root
    }

    private fun configureObserver() {
        viewModel.artifactData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.getArtifacts()
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
                        artifactAdapter.setNewList(uiState.response as List<String>)
                        rvArtifactsList.adapter = artifactAdapter
                    }
                }
            }
        }
    }

    private fun openDetails(artifactItem: String) {
        viewModel.setLoadingForDetails(binding.root.id)
        findNavController().navigate(
            ArtifactFragmentDirections.actionNavArtifactFragmentToNavArtifactDetails(artifactItem)
        )
    }
}