package com.jam.cookingina.Database

import android.content.Context
import java.io.File
import java.io.FileOutputStream

object CookingDatabaseCopier {
    fun copyDatabaseFromAssets(context: Context) {
        val databasePath = context.getDatabasePath("COOKBOOK.db").absolutePath

        if (!File(databasePath).exists()) {
            val inputStream = context.assets.open("COOKBOOK.db")
            val outputStream = FileOutputStream(databasePath)

            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
        }
    }
}

