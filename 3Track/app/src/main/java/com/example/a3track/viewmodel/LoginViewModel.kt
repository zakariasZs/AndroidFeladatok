package com.example.a3track.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3track.App
import com.example.a3track.api.TrackRepository
import com.example.a3track.api.model.LoginRequestBody
import com.example.a3track.manager.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val repository: TrackRepository) : ViewModel() {

    val TAG: String = javaClass.simpleName

    lateinit var passwordKey: String
    lateinit var email: String

    var token: MutableLiveData<String> = MutableLiveData()
    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun login() {
        val requestBody = LoginRequestBody(passwordKey, email)
        viewModelScope.launch {
            executeLogin(requestBody)
        }
    }

    private suspend fun executeLogin(requestBody: LoginRequestBody) {
        try {
            val result = withContext(Dispatchers.IO) {
                repository.login(requestBody)
            }
            token.value = result.token
            App.sharedPreferences.putStringValue(SharedPreferencesManager.KEY_TOKEN, result.token)
            isSuccessful.value = true
            Log.d(TAG, "LoginViewModel - login response: $result")
        } catch (e: Exception) {
            Log.d(TAG, "LoginViewModel - login() failed with exception: ${e.message}")
            isSuccessful.value = false
        }
    }
}