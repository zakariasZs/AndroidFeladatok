package com.example.tracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tracker.api.ThreeTrackerRepository

class MyGroupsViewModelFactory(private val repository: ThreeTrackerRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyGroupsViewModel(repository) as T
    }
}