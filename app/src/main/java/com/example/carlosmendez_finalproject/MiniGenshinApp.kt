package com.example.carlosmendez_finalproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MiniGenshinApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}