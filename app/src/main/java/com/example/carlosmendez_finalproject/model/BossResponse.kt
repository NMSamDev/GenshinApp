package com.example.carlosmendez_finalproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// boss%2Fweekly-boss
data class BossResponse(
    val name: String,
    val description: String,
    val drops: List<DropResponse>,
    val artifacts: List<Artifact>
)

@Parcelize
data class DropResponse(
    val name: String,
    val rarity: Int,
    val source: String
): Parcelable

@Parcelize
data class Artifact(
    val name: String,
    val max_rarity: Int
): Parcelable

//"bloodjade-branch",
//"dragon-lord-s-crown",
//"gilded-scale",
//"icon",
//"portrait"
