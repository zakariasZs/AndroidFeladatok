package com.example.tracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.App
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.UpdateTask
import com.example.tracker.manager.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateTaskViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun updateTask(taskId: Int, title: String, description: String, assignedToUserId: Int, priority: Int, deadline: Long, departmentId: Int, status: Int) {
        val requestBody = UpdateTask(taskId, title, description, assignedToUserId, priority, deadline, departmentId, status)
        viewModelScope.launch {
            executeTaskUpdate(requestBody)
        }
    }

    private suspend fun executeTaskUpdate(requestBody: UpdateTask) {
        viewModelScope.launch {
            try {

                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )


                val response = withContext(Dispatchers.IO) {
                    token?.let { repository.updateTask(it, requestBody) }
                }
                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Update Task response: ${response.body()}")

                    val responseToken = response.body()?.message
                    responseToken?.let {
                        Log.e("XXX Update Task Message ", it)
                        isSuccessful.value = true
                    }
                } else {
                    Log.d(TAG, "Task Update error response: ${response?.message()}")
                    isSuccessful.value = false
                }
            } catch (e: Exception) {
                Log.d(TAG, "UpdateTaskViewModel - updateTask() failed with exception: ${e.message}")
                isSuccessful.value = false
            }
        }
    }

}

