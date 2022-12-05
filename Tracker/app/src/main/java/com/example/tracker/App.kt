package com.example.tracker

import android.app.Application
import com.example.tracker.manager.SharedPreferencesManager

class App : Application() {

    companion object {
        lateinit var sharedPreferences: SharedPreferencesManager
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = SharedPreferencesManager(applicationContext)
    }
}