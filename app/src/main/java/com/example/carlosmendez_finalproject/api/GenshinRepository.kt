package com.example.carlosmendez_finalproject.api

import com.example.carlosmendez_finalproject.model.CharacterResponse
import com.example.carlosmendez_finalproject.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GenshinRepository {
    suspend fun getCharacters(): Flow<UIState>
    suspend fun getCharacterById(id: String): Flow<UIState>
    suspend fun getWeapons(): Flow<UIState>
}

class GenshinRepositoryImpl(private val service: GenshinService): GenshinRepository {
    override suspend fun getCharacters(): Flow<UIState> =
        flow {
            try {
                val response = service.getCharacters()
                if (response.isSuccessful) {
                    emit(response.body()?.let { characterResponse ->
                        UIState.Success(characterResponse)
                    } ?: throw Exception("Empty response"))
                }
                else throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getCharacterById(id: String): Flow<UIState> =
        flow {
            try {
                val response = service.getCharacterById(id)
                if (response.isSuccessful) {
                    emit(response.body()?.let { idResponse ->
                        UIState.Success(idResponse)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getWeapons(): Flow<UIState> =
        flow {
            try {
                val response = service.getWeapons()
                if (response.isSuccessful) {
                    emit(response.body()?.let { weaponResponse ->
                        UIState.Success(weaponResponse)
                    } ?: throw Exception("Empty response"))
                }
                else throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }
}