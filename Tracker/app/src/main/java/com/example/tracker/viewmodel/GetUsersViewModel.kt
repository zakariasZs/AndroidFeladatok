package com.example.tracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracker.App
import com.example.tracker.api.ThreeTrackerRepository
import com.example.tracker.api.model.GetUserResponse
import com.example.tracker.manager.SharedPreferencesManager
import kotlinx.coroutines.launch

class GetUsersViewModel(private val repository: ThreeTrackerRepository) : ViewModel()  {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    var users: MutableLiveData<List<GetUserResponse>?> = MutableLiveData()


    fun getUsers() {
        viewModelScope.launch {
            try {
                val token: String? = App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
                val response = token?.let {
                    repository.getUsers(it)
                }

                if (response?.isSuccessful == true) {
                    Log.d(TAG, "Get users response: ${response.body()}")

                    val usersList = response.body()
                    usersList?.let {
                        users.value = usersList
                        Log.e("XXX user list ", usersList.size.toString())
                    }
                } else {
                    Log.d(TAG, "Get users error response: ${response?.errorBody()}")
                }

            } catch (e: Exception) {
                Log.d(TAG, "GetUsersViewModel - getUsers() failed with exception: ${e.message}")
            }
        }
    }



    fun getUserIdFromList(position: Int): Int {
        try {
            users.value?.forEachIndexed{

                    index, element ->
                if (index == position) {
                    return element.id
                }
            }

        } catch (e: Exception) {
            Log.d(TAG, "GetUsersViewModel - getUserIdFromList() failed with exception: ${e.message}")
            return -1
        }
        return -1
    }

    fun getUserFromListByID(position: Int): String {
        try {
            users.value?.forEach{ element ->
//                Log.e("XXX- forEach ", element.id.toString())
                if (element.id.equals(position)) {
                    val userName = element.first_name + " " + element.last_name
                    return userName
                }
            }

        } catch (e: Exception) {
            Log.d(TAG, "GetUsersViewModel - getUserIdFromList() failed with exception: ${e.message}")
            return "Bad Request"
        }
        return "No User found"
    }

    fun getUserPositionInListById(id: Int): Int {
        try {
            users.value?.forEachIndexed{

                    index, element ->
                if (element.id == id) {
                    return index
                }
            }

        } catch (e: Exception) {
            Log.d(TAG, "GetUsersViewModel - getUserPositionInListById() failed with exception: ${e.message}")
            return -1
        }
        return -1
    }


}