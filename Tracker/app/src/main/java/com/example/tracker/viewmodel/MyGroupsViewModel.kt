package com.example.tracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.App
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.MyGroupsResponse
import com.example.tracker.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class MyGroupsViewModel(private val repository: ThreeTrackerRepository) : ViewModel() {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var groups: MutableLiveData<List<MyGroupsResponse>?> = MutableLiveData()
    var groupToShow : MyGroupsResponse = MyGroupsResponse( -1, "")

    init {
        getMyGroups()
    }

    private fun getMyGroups() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getMyGroups(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get group response: ${response.body()}")

                    val groupsList = response.body()
                    groupsList?.let {
                        groups.value = groupsList
                    }
                } else {
                    Log.d(TAG, "Get my groups error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "MyGroupsViewModel - getMyGroups() failed with exception: ${e.message}")
            }
        }
    }
}

