package com.jam.cookingina.Database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CookingViewModel(repository: CookingRepository) : ViewModel() {
    val allData: LiveData<List<CookingModel>> = repository.getAlldata()

    fun getAllDatas(): LiveData<List<CookingModel>> {
        return allData
    }

}
