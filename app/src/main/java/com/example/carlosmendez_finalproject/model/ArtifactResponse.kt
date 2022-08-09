package com.example.carlosmendez_finalproject.model

import com.google.gson.annotations.SerializedName

data class ArtifactResponse(
    val name: String,
    val max_rarity: Int,

    @SerializedName("1-piece_bonus")
    val piece_bonus_1: String?,
    @SerializedName("2-piece_bonus")
    val piece_bonus_2: String?,
    @SerializedName("4-piece_bonus")
    val piece_bonus_4: String?
)
