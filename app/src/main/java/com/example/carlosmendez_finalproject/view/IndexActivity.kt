package com.example.carlosmendez_finalproject.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.carlosmendez_finalproject.R
import com.google.firebase.auth.FirebaseAuth

class IndexActivity():AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
//        setupActionBarWithNavController(findNavController(R.id.nav_host_index))
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_index)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}