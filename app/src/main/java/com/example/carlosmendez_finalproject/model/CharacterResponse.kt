package com.example.carlosmendez_finalproject.model

data class CharacterResponse(
    val name: String,
    val vision: String,
    val weapon: String,
    val nation: String,
    val affiliation: String,
    val rarity: Int,
    val constellation: String,
    val birthday: String,
    val description: String,
    val skillTalents: List<Talent>,
    val passiveTalents: List<PassiveTalent>,
    val constellations: List<Constellation>
)

data class Talent(
    val name: String,
    val unlock: String,
    val description: String,
    val type: String
)

data class PassiveTalent(
    val name: String,
    val unlock: String,
    val description: String,
//    val level: Int
)


data class Constellation(
    val name: String,
    val unlock: String,
    val description: String,
    val level: Int
)

