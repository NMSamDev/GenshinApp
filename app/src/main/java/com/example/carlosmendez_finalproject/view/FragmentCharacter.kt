package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.FragmentCharacterBinding
import com.example.carlosmendez_finalproject.model.CharacterResponse
import com.example.carlosmendez_finalproject.model.UIState
import com.example.carlosmendez_finalproject.viewadapters.CharacterAdapter
import com.example.carlosmendez_finalproject.viewmodel.GenshinViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class FragmentCharacter: Fragment() {
    lateinit var binding: FragmentCharacterBinding
    private val characterAdapter by lazy {
        CharacterAdapter(openDetails = ::openDetails)
    }
    private val viewModel: GenshinViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(layoutInflater)
        if(viewModel!=null)
            configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        Log.d("CHARACTER", "configureObserver: INIT")
        viewModel.characterData.observe(viewLifecycleOwner) {
            uiState ->
            if (uiState!=null)
                Log.d("CHARACTER", "configureObserver: ALGO")
            else
                Log.d("CHARACTER", "configureObserver: NONE")
            when(uiState) {
                is UIState.Loading -> {
                    Log.d("CHARACTER", "configureObserver: LOADING")
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