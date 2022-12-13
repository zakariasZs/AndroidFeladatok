package com.example.tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.example.tracker.databinding.ActivityMainBinding
import com.example.tracker.fragment.LoginFragment
import com.example.tracker.fragment.SplashScreenFragment

class MainActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called!")
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(supportFragmentManager.findFragmentById(R.id.splashScreenFragment) is SplashScreenFragment || supportFragmentManager.findFragmentById(R.id.loginFragment) is LoginFragment ){
            binding.bottomNavigationView.visibility = View.INVISIBLE
        }

        binding.bottomNavigationView.setSelectedItemId(R.id.my_tasks);

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.my_tasks -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.tasksFragment)
                }
                R.id.settings -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.userProfileFragment)
                }
                R.id.my_groups -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.myGroupsFragment)
                }
                R.id.my_activities -> {
//                    findNavController(R.id.nav_host_fragment).navigate(R.id.tasksFragment)
                }
                else ->{
                    false
                }
            }
            true
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called!")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called!")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called!")
    }


}