package com.example.a3track.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a3track.api.MarketPlaceRepository

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
class LoginViewModelFactory(private val repository: MarketPlaceRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}