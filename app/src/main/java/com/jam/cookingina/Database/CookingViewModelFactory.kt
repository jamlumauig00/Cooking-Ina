package com.jam.cookingina.Database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CookingViewModelFactory(private val repository: CookingRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CookingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CookingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}