package com.example.carlosmendez_finalproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carlosmendez_finalproject.api.GenshinRepository
import com.example.carlosmendez_finalproject.model.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
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

    fun getCharacterById(id: String) {
        viewModelSafeScope.launch(dispatcher) {
            repository.getCharacterById(id).collect{ state ->
                _characterDetails.postValue(state)
            }
        }
    }
    fun setLoading() {
        _characterData.value = UIState.Loading
    }

    fun setLoadingForDetails() {
        _characterDetails.value = UIState.Loading
    }
}