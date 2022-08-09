package com.example.carlosmendez_finalproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carlosmendez_finalproject.R
import com.example.carlosmendez_finalproject.api.GenshinRepository
import com.example.carlosmendez_finalproject.model.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

private const val TAG = "GenshinViewModel"
class GenshinViewModel(
    private val repository: GenshinRepository,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {
    private val _characterData = MutableLiveData<UIState>()
    val characterData: LiveData<UIState> get() = _characterData

    private  val _characterDetails = MutableLiveData<UIState>()
    val characterDetails: LiveData<UIState> get() = _characterDetails

    private val _weaponData = MutableLiveData<UIState>()
    val weaponData: LiveData<UIState> get() = _weaponData

    private  val _weaponDetails = MutableLiveData<UIState>()
    val weaponDetails: LiveData<UIState> get() = _weaponDetails

    private val _artifactData = MutableLiveData<UIState>()
    val artifactData: LiveData<UIState> get() = _artifactData

    private  val _artifactDetails = MutableLiveData<UIState>()
    val artifactDetails: LiveData<UIState> get() = _artifactDetails

    private val _weeklyBossData = MutableLiveData<UIState>()
    val weeklyBossData: LiveData<UIState> get() = _weeklyBossData

    private  val _weeklyBossDetails = MutableLiveData<UIState>()
    val weeklyBossDetails: LiveData<UIState> get() = _weeklyBossDetails


    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(TAG, "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",throwable)
        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }
    fun getCharacters() {
        viewModelSafeScope.launch(dispatcher) {
            // collect from our flow
            repository.getCharacters().collect { state ->
                // postValue updates LiveData asynchronously
                _characterData.postValue(state)
            }
        }
    }

    fun getWeapons() {
        viewModelSafeScope.launch(dispatcher) {
            repository.getWeapons().collect() { state ->
                _weaponData.postValue(state)
            }
        }
    }

    fun getArtifacts() {
        viewModelSafeScope.launch(dispatcher) {
            repository.getArtifacts().collect() { state ->
                _artifactData.postValue(state)
            }
        }
    }

    fun getWeeklyBosses() {
        viewModelSafeScope.launch(dispatcher) {
            repository.getWeeklyBosses().collect() { state ->
                _weeklyBossData.postValue(state)
            }
        }
    }

    fun getCharacterById(id: String) {
        viewModelSafeScope.launch(dispatcher) {
            repository.getCharacterById(id).collect{ state ->
                _characterDetails.postValue(state)
            }
        }
    }

    fun getWeaponById(id: String) {
        viewModelSafeScope.launch(dispatcher) {
            repository.getWeaponsById(id).collect{ state ->
                _weaponDetails.postValue(state)
            }
        }
    }

    fun getArtifactById(id: String){
        viewModelSafeScope.launch(dispatcher) {
            repository.getArtifactsById(id).collect{ state ->
                _artifactDetails.postValue(state)
            }
        }
    }

    fun getWeeklyBossById(id: String) {
        viewModelSafeScope.launch(dispatcher) {
            repository.getWeeklyBossesById(id).collect{ state ->
                _weeklyBossDetails.postValue(state)
            }
        }
    }

    fun setLoading(select: Int) {
        when(select){
            R.id.btn_characters -> _characterData.value = UIState.Loading
            R.id.btn_weapons -> _weaponData.value = UIState.Loading
            R.id.btn_artifacts -> _artifactData.value = UIState.Loading
            R.id.btn_bosses -> _weeklyBossData.value = UIState.Loading
        }
    }

    fun setLoadingForDetails(select: Int) {
        when(select){
            R.id.layout_fragment_character -> _characterDetails.value = UIState.Loading
            R.id.layout_fragment_weapon -> _weaponDetails.value = UIState.Loading
            R.id.layout_fragment_artifact -> _artifactDetails.value = UIState.Loading
            R.id.layout_fragment_weeklyboss -> _weeklyBossDetails.value = UIState.Loading
        }
    }
}