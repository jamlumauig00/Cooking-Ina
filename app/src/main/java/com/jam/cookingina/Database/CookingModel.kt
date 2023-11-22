package com.jam.cookingina.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableName")
data class CookingModel(
    @PrimaryKey val ID: String = "",
    val Category: String?,
    val Cuisine: String?,
    val Description: String?,
    val Ingredients: String?,
    val How: String?
)

