package com.example.carlosmendez_finalproject.model

// boss%2Fweekly-boss
data class BossResponse(
    val name: String,
    val description: String,
    val drops: List<Drop>,
    val artifacts: List<Artifact>
)

data class Drop(
    val name: String,
    val rarity: Int,
    val source: String
)

data class Artifact(
    val name: String,
    val max_rarity: Int
)

//"bloodjade-branch",
//"dragon-lord-s-crown",
//"gilded-scale",
//"icon",
//"portrait"
