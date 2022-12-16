package com.example.tracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.App
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.ActivitiesResponse
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class ActivitiesViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var activities: MutableLiveData<List<ActivitiesResponse>?> = MutableLiveData()

    init {
        getActivities()
    }

    fun getActivities() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getActivities(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get activities response: ${response.body()}")

                    val activitiesList = response.body()
                    activitiesList?.let {
                        activities.value = activitiesList
                    }
                } else {
                    Log.d(TAG, "Get activities error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "ActivitiesViewModel - getActivities() failed with exception: ${e.message}")
            }
        }
    }

}

