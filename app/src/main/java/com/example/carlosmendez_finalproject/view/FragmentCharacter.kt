package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.FragmentCharacterBinding
import com.example.carlosmendez_finalproject.model.CharacterResponse
import com.example.carlosmendez_finalproject.model.UIState
import com.example.carlosmendez_finalproject.viewadapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCharacter: ViewModelFragment() {
    lateinit var binding: FragmentCharacterBinding
    private val characterAdapter by lazy {
        CharacterAdapter(openDetails = ::openDetails)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.characterData.observe(viewLifecycleOwner) {
            uiState ->
            when(uiState) {
                is UIState.Loading -> {
                    viewModel.getCharacters()
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
                        characterAdapter.setCharacterList(uiState.response as List<String>)
                        rvCharacterList.adapter = characterAdapter
                    }
                }
            }
        }
    }

     private fun openDetails(characterItem: String) {
        viewModel.setLoadingForDetails(binding.root.id)
        findNavController().navigate(
            FragmentCharacterDirections.actionNavCharacterFragmentToCharacterDetails(characterItem)
        )
    }
}