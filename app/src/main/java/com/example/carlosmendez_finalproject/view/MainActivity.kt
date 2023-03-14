package com.example.carlosmendez_finalproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carlosmendez_finalproject.R
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}