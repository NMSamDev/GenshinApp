package com.example.carlosmendez_finalproject.model

data class ElementResponse(
    val name: String,
    val key: String,
    val reactions: List<Reaction>
)

data class Reaction(
    val name: String,
    val elements: List<String>,
    val description: String,
    )
// icon