package com.example.tracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.App
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.TaskPostBody
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.api.model.UpdateProfile
import com.example.tracker.manager.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateProfileViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun updateProfile(lastName: String, firstName: String, location: String, phoneNumber: String, imageUrl: String) {
        val requestBody = UpdateProfile(lastName, firstName, location, phoneNumber, imageUrl)
        viewModelScope.launch {
            executeProfileUpdate(requestBody)
        }
    }

    private suspend fun executeProfileUpdate(requestBody: UpdateProfile) {
        viewModelScope.launch {
            try {

                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )


                val response = withContext(Dispatchers.IO) {
                    token?.let { repository.updateProfile(it, requestBody) }
                }
                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Update Profile response: ${response.body()}")

                    val responseToken = response.body()?.message
                    responseToken?.let {
                        Log.e("XXX Update Profile Message ", it)
                        isSuccessful.value = true
                    }
                } else {
                    Log.d(TAG, "Profile Update error response: ${response?.message()}")
                    isSuccessful.value = false
                }
            } catch (e: Exception) {
                Log.d(TAG, "UpdateProfileViewModel - updateProfile() failed with exception: ${e.message}")
                isSuccessful.value = false
            }
        }
    }

}

