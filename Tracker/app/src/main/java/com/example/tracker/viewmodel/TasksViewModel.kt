package com.example.tracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.App
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class TasksViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var tasks: MutableLiveData<List<TasksResponse>?> = MutableLiveData()
    var currentItemIndex = -1

    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getTasks(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get tasks response: ${response.body()}")

                    val tasksList = response.body()
                    tasksList?.let {
                        tasks.value = tasksList
                    }
                } else {
                    Log.d(TAG, "Get tasks error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "TasksViewModel - getTasks() failed with exception: ${e.message}")
            }
        }
    }
}

