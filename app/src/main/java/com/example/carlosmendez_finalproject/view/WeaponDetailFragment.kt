package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.carlosmendez_finalproject.R
import com.example.carlosmendez_finalproject.databinding.WeaponDetailBinding
import com.example.carlosmendez_finalproject.model.UIState
import com.example.carlosmendez_finalproject.model.WeaponResponse

class WeaponDetailFragment: ViewModelFragment() {
    lateinit var binding: WeaponDetailBinding
    private val args: WeaponDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeaponDetailBinding.inflate(layoutInflater)
        configureObserver()

        return binding.root
    }

    private fun configureObserver() {
        viewModel.weaponDetails.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    viewModel.getWeaponById(args.weaponId)
                }
                is UIState.Error -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                    }
                }
                is UIState.Success<*> -> {
                    val weaponItem = (state.response as WeaponResponse)
                    val baseUrl = "https://api.genshin.dev/weapons"
                    binding.apply {
                        pbLoading.visibility = View.GONE

                        Glide.with(ivWeaponIcon)
                            .load("$baseUrl/${args.weaponId}/icon")
                            .error(R.drawable.paimon_icon_0)
                            .into(ivWeaponIcon)
                        ivWeaponIcon.setBackgroundResource(
                            when(weaponItem.rarity) {
                                5 -> R.color.geo
                                4 -> R.color.electro
                                3 -> R.color.hydro
                                2 -> R.color.anemo
                                else -> R.color.hintColor1
                            }
                        )


                        Glide.with(ivWeaponType)
                            .load(
                                when(weaponItem.type) {
                                    "Bow" -> R.drawable.weapon_icon_bow
                                    "Polearm" -> R.drawable.weapon_icon_polearm
                                    "Sword" -> R.drawable.weapon_icon_sword
                                    "Claymore" -> R.drawable.weapon_icon_claymore
                                    else -> R.drawable.weapon_icon_catalyst
                                }
                            )
                            .into(ivWeaponType)
                        tvWeaponAtk.text = weaponItem.baseAttack.toString()
                        rbWeaponRarity.numStars = weaponItem.rarity
                        tvWeaponName.text = weaponItem.name
                        tvAbilityName.text = weaponItem.passiveName
                        tvAbilityDescription.text = weaponItem.passiveDesc
                    }
                }
            }

        }
    }
}