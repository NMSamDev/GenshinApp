package com.example.carlosmendez_finalproject.model

data class WeaponResponse(
    val name: String,
    val type: String,
    val rarity: Int,
    val baseAttack: Int,
    val subStat: String,
    val passiveName: String,
    val passiveDesc: String,
    val location: String,
    val ascensionMaterial: String
)
