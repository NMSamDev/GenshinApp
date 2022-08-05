package com.example.carlosmendez_finalproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(
    val name: String,
    val title: String,
    val vision: String,
    val weapon_type: String,
    val nation: String,
    val affiliation: String,
    val rarity: Int,
    val constellation: String,
    val birthday: String,
    val description: String,
    val skillTalents: List<Talent>,
    val passiveTalents: List<PassiveTalent>,
    val constellations: List<Constellation>
): Parcelable

@Parcelize
data class Talent(
    val name: String,
    val unlock: String,
    val description: String,
    val type: String
): Parcelable

@Parcelize
data class PassiveTalent(
    val name: String,
    val unlock: String,
    val description: String,
//    val level: Int
): Parcelable

@Parcelize
data class Constellation(
    val name: String,
    val unlock: String,
    val description: String,
    val level: Int
): Parcelable

