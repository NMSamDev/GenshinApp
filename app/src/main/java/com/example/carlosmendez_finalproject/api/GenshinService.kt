package com.example.carlosmendez_finalproject.api


import com.example.carlosmendez_finalproject.model.ArtifactResponse
import com.example.carlosmendez_finalproject.model.BossResponse
import com.example.carlosmendez_finalproject.model.CharacterResponse
import com.example.carlosmendez_finalproject.model.WeaponResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Service is for calling an specific path of the base url
interface GenshinService {
    @GET("characters")
    suspend fun getCharacters(): Response<List<String>>

    @GET("characters/{id}")
    suspend fun getCharacterById(
        @Path("id") id: String
    ): Response<CharacterResponse>

    @GET("weapons")
    suspend fun getWeapons(): Response<List<String>>

    @GET("weapons/{id}")
    suspend fun getWeaponById(
        @Path("id") id: String
    ): Response<WeaponResponse>

    @GET("artifacts")
    suspend fun getArtifacts(): Response<List<String>>

    @GET("artifacts/{id}")
    suspend fun getArtifactById(
        @Path("id") id: String
    ): Response<ArtifactResponse>

    @GET("boss%2Fweekly-boss")
    suspend fun getWeeklyBosses(): Response<List<String>>

    @GET("boss%2Fweekly-boss/{id}")
    suspend fun getWeeklyBossById(
        @Path("id") id: String
    ): Response<BossResponse>

}