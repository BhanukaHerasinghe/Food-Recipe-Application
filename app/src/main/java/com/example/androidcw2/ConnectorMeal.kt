package com.example.androidcw2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CategoryOfFood::class], version = 1)
abstract class ConnectorMeal:RoomDatabase() {
    abstract fun categoryOfFoodDao(): CategoryOfFoodDao
}