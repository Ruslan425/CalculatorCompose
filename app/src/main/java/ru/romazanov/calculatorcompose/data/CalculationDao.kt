package ru.romazanov.calculatorcompose.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CalculationDao {

    @Query("SELECT * FROM calculation")
    fun getAll(): List<Calculation>

    @Insert
    suspend fun addCalculation(calculation: Calculation)

    @Query("DELETE FROM calculation")
    suspend fun deleteAll()


}


