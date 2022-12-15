package com.example.tracker.viewmodel

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tracker.App
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class UserProfileViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    var last_name: MutableLiveData<String> = MutableLiveData()
    var first_name: MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var location: MutableLiveData<String> = MutableLiveData()
    var phone_number: MutableLiveData<String> = MutableLiveData()


    fun getUserProfile() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getUserProfileDetail(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get user profile details: ${response.body()}")

                    val userDetails = response.body()
                    userDetails?.let {
                        last_name.value = it.last_name
                        first_name.value = it.first_name
                        email.value = it.email
                        location.value = it.location
                        phone_number.value = it.phone_number
                        Log.e("XXX-user details", last_name.value+","+first_name.value+","+email.value+","+location.value+","+phone_number.value)
                        isSuccessful.value = true
                    }
                } else {
                    Log.d(TAG, "Get user profile details error response: ${response?.errorBody()}")
                    isSuccessful.value = false
                }

            } catch (e: Exception) {
                Log.d(TAG, "UserProfileViewModel - getUserProfile() failed with exception: ${e.message}")
                isSuccessful.value = false
            }
        }
    }

}