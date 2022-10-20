package com.example.carlosmendez_finalproject.view

import com.example.carlosmendez_finalproject.model.CharacterResponse
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FragmentCharacterDetailsTest{

    @Before
    fun setUp() {
        clearAllMocks()
        unmockkAll()
    }

    @Test
    fun test_isCharacterDataVisible() {
        // Setup
        val characterId = "amber"
        val name = "Amber"
        val title = "Gliding Champion"
        val vision = "Pyro"
        val weapon = "Bow"
        val nation = "Mondstadt"
        val affiliation = "Knights of Favonius"
        val rarity = 4
        val constellation = "Lepus"
        val birthday = "0000-08-10"
        val description ="Always energetic and full of life, Amber's the best - albeit only - Outrider of the Knights of Favonius."

        val character = CharacterResponse(
            name = name,
            title = title,
            vision = vision,
            weapon_type = weapon,
            nation = nation,
            affiliation = affiliation,
            rarity = rarity,
            constellation = constellation,
            birthday = birthday,
            description = description,
            skillTalents = listOf(),
            passiveTalents = listOf(),
            constellations = listOf()
        )

    val doc1: FragmentCharacterDetails = mockk()
        every {
            doc1
        }
    }
}