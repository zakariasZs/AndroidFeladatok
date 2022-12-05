package com.example.a3track

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.a3track.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun setBottomNavigationVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
        binding.bottomNavigationView.visibility = visibility
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart() function called")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop() function called")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause() function called")
    }
}