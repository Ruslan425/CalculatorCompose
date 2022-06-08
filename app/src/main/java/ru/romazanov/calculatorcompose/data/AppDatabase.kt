package ru.romazanov.calculatorcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        Calculation::class
    ]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun calculateDao(): CalculationDao
}



