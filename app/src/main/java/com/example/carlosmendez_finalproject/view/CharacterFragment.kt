package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carlosmendez_finalproject.databinding.CharacterFragmentBinding
import com.example.carlosmendez_finalproject.model.UIState
import com.example.carlosmendez_finalproject.viewadapters.CharacterAdapter

class CharacterFragment: ViewModelFragment() {
    lateinit var binding: CharacterFragmentBinding
    private val characterAdapter by lazy {
        CharacterAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterFragmentBinding.inflate(layoutInflater)
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
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is UIState.Success<*> -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        characterAdapter.setCharacterList(uiState.response as List<String>)
                        rvCharacterList.adapter = characterAdapter
                    }
                }
            }
        }
    }
}