package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.carlosmendez_finalproject.databinding.WeeklyBossDetailBinding
import com.example.carlosmendez_finalproject.model.BossResponse
import com.example.carlosmendez_finalproject.model.UIState
import com.example.carlosmendez_finalproject.viewadapters.DropAdapter

class WeeklyBossDetailFragment: ViewModelFragment() {
    lateinit var binding: WeeklyBossDetailBinding
    private val args: WeeklyBossDetailFragmentArgs by navArgs()
    private val dropAdapter by lazy {
        DropAdapter(bossId = args.bossId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeeklyBossDetailBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.weeklyBossDetails.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    viewModel.getWeeklyBossById(args.bossId)
                }
                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                }
                is UIState.Success<*> -> {
                    val bossItem = (state.response as BossResponse)
                    val baseUrl = "https://api.genshin.dev/boss%2Fweekly-boss"

                    binding.apply {
                        binding.pbLoading.visibility = View.GONE
                        Glide.with(ivBossPortrait)
                            .load("$baseUrl/${args.bossId}/portrait")
                            .into(ivBossPortrait)

                        tvBossName.text = bossItem.name
                        tvBossDescription.text = bossItem.description
                        dropAdapter.setNewList(bossItem.drops)
                        rvDrop.adapter = dropAdapter
                    }
                }
            }
        }
    }
}