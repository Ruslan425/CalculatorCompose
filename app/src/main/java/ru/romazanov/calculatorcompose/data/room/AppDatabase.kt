package ru.romazanov.calculatorcompose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        CalculationEntity::class
    ]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun calculateDao(): CalculationDao
}



