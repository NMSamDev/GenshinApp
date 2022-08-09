package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.carlosmendez_finalproject.R
import com.example.carlosmendez_finalproject.databinding.ArtifactDetailBinding
import com.example.carlosmendez_finalproject.model.ArtifactResponse
import com.example.carlosmendez_finalproject.model.UIState

class ArtifactDetailFragment:ViewModelFragment() {
    lateinit var binding: ArtifactDetailBinding
    private val args: ArtifactDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ArtifactDetailBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.artifactDetails.observe(viewLifecycleOwner){ state ->
            when (state) {
                is UIState.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    viewModel.getArtifactById(args.artifactId)
                }
                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                }
                is UIState.Success<*> -> {
                    val artifactItem = (state.response as ArtifactResponse)
                    val baseUrl = "https://api.genshin.dev/artifacts"
                    binding.apply {
                        Glide.with(ivArtifactIcon0)
                            .load("$baseUrl/${args.artifactId}/circlet-of-logos")
                            .error(R.drawable.paimon_icon_0)
                            .into(ivArtifactIcon0)

                        binding.pbLoading.visibility = View.GONE
                        if(artifactItem.piece_bonus_1 == null){
                            tvArtifact2Desc.text = artifactItem.piece_bonus_2
                            tvArtifact4Desc.text = artifactItem.piece_bonus_4
                        }
                        else {
                            tvArtifact2Title.visibility = View.GONE
                            tvArtifact2Desc.visibility = View.GONE
                            tvArtifact4Title.visibility = View.GONE
                            tvArtifact4Desc.visibility = View.GONE
                            tvArtifact1Title.visibility = View.VISIBLE
                            tvArtifact1Desc.visibility = View.VISIBLE
                            tvArtifact1Desc.text = artifactItem.piece_bonus_1
                        }
                        tvArtifactName.text = artifactItem.name
                    }
                }
            }

        }
    }
}