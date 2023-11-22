package com.jam.cookingina.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query


@Dao
interface CookingDao {
    @Query("SELECT * FROM tableName")
    fun getAllTableElements(): LiveData<List<CookingModel>>

    @Query("SELECT * FROM tableName WHERE ID = :id")
   // suspend fun getTableElementById(id: String): TableElement
    fun getTableElementById(id: String): LiveData<List<CookingModel>>

}
