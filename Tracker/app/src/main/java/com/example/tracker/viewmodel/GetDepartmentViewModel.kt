package com.example.tracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.App
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.GetDepartmentResponse
import com.example.tracker.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class GetDepartmentViewModel(private val repository: ThreeTrackerRepository) : ViewModel()  {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var departments: MutableLiveData<List<GetDepartmentResponse>?> = MutableLiveData()


    fun getDepartments() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getDepartments(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get users response: ${response.body()}")

                    val departmentList = response.body()
                    departmentList?.let {
                        departments.value = departmentList
                        Log.e("XXX departments list: ", departmentList.size.toString())
                    }
                } else {
                    Log.d(TAG, "Get departments error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "GetDepartmentViewModel - getDepartments() failed with exception: ${e.message}")
            }
        }
    }
    fun getDepartmentIdFromList(position: Int): Int {
        try {
            departments.value?.forEachIndexed{

                    index, element ->
                if (index == position) {
                    return element.id
                }
            }

        } catch (e: Exception) {
            Log.d(TAG, "GetDepartmentViewModel - getDepartmentIdFromList() failed with exception: ${e.message}")
            return -1
        }
        return -1
    }

    fun getDepartmentPositionFromList(id: Int): Int {
        try {
            departments.value?.forEachIndexed{

                    index, element ->
                if (element.id == id) {
                    return index
                }
            }

        } catch (e: Exception) {
            Log.d(TAG, "GetDepartmentViewModel - getDepartmentIdFromList() failed with exception: ${e.message}")
            return -1
        }
        return -1
    }

}