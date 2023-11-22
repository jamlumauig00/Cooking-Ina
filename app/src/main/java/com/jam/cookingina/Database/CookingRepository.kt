package com.jam.cookingina.Database

import androidx.lifecycle.LiveData

class CookingRepository(private val tableElementsDao: CookingDao) {

 val getAlldata : LiveData<List<CookingModel>> = tableElementsDao.getAllTableElements()

  fun getAlldata(): LiveData<List<CookingModel>> {
    return tableElementsDao.getAllTableElements()
  }

}
