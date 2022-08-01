package com.example.carlosmendez_finalproject.model

sealed class UIState {
    object Loading: UIState()
    class Error(val error: Exception): UIState()
    class Success<T>(val response: T): UIState()
}

enum class StateUI {
    LOADING, // 0
    ERROR, // 1
    SUCCESS // 2
}

