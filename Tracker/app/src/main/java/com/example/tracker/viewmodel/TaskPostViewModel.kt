package com.example.tracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.App
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.TaskPostBody
import com.example.tracker.manager.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskPostViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var isSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    fun taskPost(title: String, description: String, assigneeToUserId: Int, priority: Int, deadline: Long, departmentId: Int, status: Int) {
        val requestBody = TaskPostBody(title, description, assigneeToUserId, priority, deadline, departmentId, status)
        viewModelScope.launch {
            executeTaskCreation(requestBody)
        }
    }

    private suspend fun executeTaskCreation(requestBody: TaskPostBody) {
        viewModelScope.launch {
            try {

                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )

//            val response = token?.let {
//                repository.postTask(it, requestBody)
//            }

                val response = withContext(Dispatchers.IO) {
                    token?.let { repository.postTask(it, requestBody) }
                }
                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Task Post response: ${response.body()}")

                    val responseToken = response.body()?.message
                    responseToken?.let {
                        Log.e("XXX Task Post Message ", it)
                        isSuccessful.value = true
                    }
                } else {
                    Log.d(TAG, "Task creation error response: ${response?.message()}")
                    isSuccessful.value = false
                }
            } catch (e: Exception) {
                Log.d(TAG, "TaskPostViewModel - taskPost() failed with exception: ${e.message}")
                isSuccessful.value = false
            }
        }
    }
}