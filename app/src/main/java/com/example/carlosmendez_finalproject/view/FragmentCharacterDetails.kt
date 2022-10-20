package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.carlosmendez_finalproject.R
import com.example.carlosmendez_finalproject.databinding.FragmentCharacterDetailBinding
import com.example.carlosmendez_finalproject.model.CharacterResponse
import com.example.carlosmendez_finalproject.model.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCharacterDetails: ViewModelFragment() {
    lateinit var binding: FragmentCharacterDetailBinding
    private val args: FragmentCharacterDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.characterDetails.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    viewModel.getCharacterById(args.characterId)

                }
                is UIState.Error -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                    }
                }
                is UIState.Success<*> -> {

                    val characterItem = (state.response as CharacterResponse)
                    val baseUrl = "https://api.genshin.dev"
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvCharacterName.text = characterItem.name
                        tvCharacterTitle.text = characterItem.title
                        tvCharacterDescription.text = characterItem?.description
                        rbCharacterRarity.numStars = characterItem.rarity

                        Glide.with(ivCharacterGachaSplash)
                            .load("$baseUrl/characters/${args.characterId}/gacha-splash")
                            .error("$baseUrl/characters/${args.characterId}/portrait")
                            .into(ivCharacterGachaSplash)

                        Glide.with(ivCharacterElement)
                            .load("$baseUrl/elements/${characterItem.vision.lowercase()}/icon")
                            .into(ivCharacterElement)
                        Glide.with(ivCharacterWeapon)
                            .load(
                                when(characterItem.weapon_type) {
                                    "BOW" -> R.drawable.weapon_icon_bow
                                    "POLEARM" -> R.drawable.weapon_icon_polearm
                                    "SWORD" -> R.drawable.weapon_icon_sword
                                    "CLAYMORE" -> R.drawable.weapon_icon_claymore
                                    else -> R.drawable.weapon_icon_catalyst
                                }
                            )
                            .into(ivCharacterWeapon)
                        Glide.with(ivCharacterNation)
                            .load("$baseUrl/nations/${characterItem.nation.lowercase()}/icon")
                            .into(ivCharacterNation)

                        ivCharacterGachaSplash.setBackgroundResource(
                            when (characterItem.vision.lowercase()) {
                                "anemo" -> R.color.anemo
                                "cryo" -> R.color.cryo
                                "electro" -> R.color.electro
                                "geo" -> R.color.geo
                                "hydro" -> R.color.hydro
                                "pyro" -> R.color.pyro
                                else -> R.color.dendro
                            }
                        )
                    }
                }
            }
        }
    }
}