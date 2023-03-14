package com.example.carlosmendez_finalproject.api

import android.util.Log
import com.example.carlosmendez_finalproject.model.CharacterResponse
import com.example.carlosmendez_finalproject.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GenshinRepository {
    suspend fun getCharacters(): Flow<UIState>
    suspend fun getCharacterById(id: String): Flow<UIState>
    suspend fun getWeapons(): Flow<UIState>
    suspend fun getWeaponsById(id: String): Flow<UIState>
    suspend fun getArtifacts(): Flow<UIState>
    suspend fun getArtifactsById(id: String): Flow<UIState>
    suspend fun getWeeklyBosses(): Flow<UIState>
    suspend fun getWeeklyBossesById(id: String): Flow<UIState>
}

class GenshinRepositoryImpl(private val service: GenshinService): GenshinRepository {
    override suspend fun getCharacters(): Flow<UIState> =
        flow {
            Log.d("MENU", "getCharacters: INICIO")
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

    override suspend fun getWeaponsById(id: String): Flow<UIState> =
        flow {
            try {
                val response = service.getWeaponById(id)
                if (response.isSuccessful) {
                    emit(response.body()?.let { idResponse ->
                        UIState.Success(idResponse)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getArtifacts(): Flow<UIState> =
        flow {
            try {
                val response = service.getArtifacts()
                if (response.isSuccessful) {
                    emit(response.body()?.let { artifactResponse ->
                        UIState.Success(artifactResponse)
                    } ?: throw Exception("Empty response"))
                }
                else throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getArtifactsById(id: String): Flow<UIState> =
        flow {
            try {
                val response = service.getArtifactById(id)
                if (response.isSuccessful) {
                    emit(response.body()?.let { idResponse ->
                        UIState.Success(idResponse)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }


    override suspend fun getWeeklyBosses(): Flow<UIState> =
        flow {
            try {
                val response = service.getWeeklyBosses()
                if (response.isSuccessful) {
                    emit(response.body()?.let { wBossResponse ->
                        UIState.Success(wBossResponse)
                    } ?: throw Exception("Empty response"))
                }
                else throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getWeeklyBossesById(id: String): Flow<UIState> =
        flow {
            try {
                val response = service.getWeeklyBossById(id)
                if (response.isSuccessful) {
                    emit(response.body()?.let { idResponse ->
                        UIState.Success(idResponse)
                    } ?: throw Exception("Empty response"))
                } else throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

}